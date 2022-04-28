import logging

import smtplib
from email.mime.text import MIMEText
from email.header import Header
from apscheduler.schedulers.blocking import BlockingScheduler

from pynvml import *

class Monitor():

    def __init__(self):
        self.from_addr = "...." # 邮箱 qq邮箱
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
        except Exception as e:
            try:
                email_client.login(self.from_addr, self.from_code)
            except Exception as e:
                print(e)
        else:
            email_client.sendmail(self.from_addr, receive_email_address,
                                  message.as_string())
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

    if monitor.status is True and monitor.send_flag == "0":
        message_subject = "产生空余GPU"
        message_context = monitor.infor
        for to_addr in monitor.to_addr:
            receive_email_address = to_addr
            monitor.process(message_context, receive_email_address, message_subject)
        with open("send_flag", 'w') as f:
            f.write("1")

    elif monitor.status is False and monitor.send_flag == "1":
        message_subject = '空余GPU已被使用'
        message_context = monitor.infor
        for to_addr in monitor.to_addr:
            receive_email_address = to_addr
            monitor.process(message_context, receive_email_address, message_subject)
        with open("send_flag", 'w') as f:
            f.write("0")


if __name__ == "__main__":
    print("自动监控GPU程序 运行中...")
    sched.start()
