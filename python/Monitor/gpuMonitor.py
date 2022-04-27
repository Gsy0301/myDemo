import logging

import smtplib
from email.mime.text import MIMEText
from email.header import Header
from apscheduler.schedulers.blocking import BlockingScheduler

from pynvml import *

logging.basicConfig(
    level=logging.INFO,
    format='%(asctime)s - %(name)s - %(levelname)s - %(message)s',
    filename='gpuMonitor.log',
    filemode='w')
logger = logging.getLogger(__name__)


class Monitor():

    def __init__(self):
        self.from_name = "..." #邮箱名 可不填，可不填
        self.from_addr = "...." # 邮箱 qq邮箱
        self.from_pwd = "...." # 邮箱密码，可不填
        self.from_code = "..." # 邮箱 qq smtp授权码
        self.to_addr = ["...", "...", "..."] # 接收邮箱
        self.send_flag = False

    def getGpuInfo(self):
        risk = 10
        infor = {}
        status = False
        status_list = []
        nvmlInit()
        count = nvmlDeviceGetCount()
        for i in range(count):
            handle = nvmlDeviceGetHandleByIndex(i)
            name = nvmlDeviceGetName(handle)
            info = nvmlDeviceGetMemoryInfo(handle)
            infor[str(i)] = '{}-第{}号GPU使用量：{}, 空余:{}M'.format(name.decode('utf-8'), i,
                                                              '%.2f%%' % (100 * info.used / info.total),
                                                              (info.total - info.used) / 1024 / 1024)
            status_list.append(100 * info.used / info.total)
        nvmlShutdown()
        print(status_list)
        for i in status_list:
            if i <= risk:
                status = True
        self.status = status
        self.infor = str(infor)

    def process(self, message_context, receive_email_address, message_subject):
        smtp_server_host = "smtp.qq.com"
        semtp_server_post = "465"

        message = MIMEText(message_context, 'plain', 'utf-8')
        message['From'] = Header(self.from_addr, 'utf-8')
        message['To'] = Header(receive_email_address, 'utf-8')
        message['Subject'] = Header(message_subject, 'utf-8')
        email_client = smtplib.SMTP_SSL(smtp_server_host, semtp_server_post)
        try:
            email_client.login(self.from_addr, self.from_code)
            logger.info(
                "smtp----login sucess,now will send email to {}".format(
                    receive_email_address))
        except Exception as e:
            logger.info(e)
            try:
                logger.info('smtp----login again.....')
                email_client.login(self.from_addr, self.from_code)
                logger.info(
                    "smtp----login sucess,now will send email to {}".format(
                        receive_email_address))
            except Exception as e:
                logger.info(e)
                logger.info(
                    'smtp----sorry,check yourusername or yourpassword not correct or another problem occur'
                )
        else:
            email_client.sendmail(self.from_addr, receive_email_address,
                                  message.as_string())
            logger.info('smtp----send email to {} finish'.format(
                receive_email_address))
        finally:
            email_client.close()


### 设定定时监控
sched = BlockingScheduler()


@sched.scheduled_job('interval', seconds=10)
def main():
    monitor = Monitor()
    monitor.getGpuInfo()
    with open("send_flag", 'r') as f:
        monitor.send_flag = f.readline()

    logger.info("status:{}, send_flag: {}".format(monitor.status, monitor.send_flag))
    print("status:{}, send_flag: {}".format(monitor.status, monitor.send_flag))
    if monitor.status is True and monitor.send_flag == "0":
        message_subject = "产生空余GPU"
        print("产生空余GPU")
        message_context = monitor.infor
        for to_addr in monitor.to_addr:
            receive_email_address = to_addr
            monitor.process(message_context, receive_email_address, message_subject)
            logger.info("产生空余GPU already send to {}".format(to_addr))
        with open("send_flag", 'w') as f:
            f.write("1")

    elif monitor.status is False and monitor.send_flag == "1":
        print("空余GPU已被使用")
        message_subject = '空余GPU已被使用'
        message_context = monitor.infor
        for to_addr in monitor.to_addr:
            receive_email_address = to_addr
            monitor.process(message_context, receive_email_address, message_subject)
            logger.info("空余GPU已被使用 already send to {}".format(to_addr))
        with open("send_flag", 'w') as f:
            f.write("0")


if __name__ == "__main__":
   sched.start()
