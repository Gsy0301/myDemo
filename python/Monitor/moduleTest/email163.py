import smtplib
from email.mime.text import MIMEText
from email.header import Header

from pynvml import *


class Monitor():
    def __init__(self):
        self.from_addr = "..."  # 邮箱 163邮箱
        self.from_code = "..."  # 邮箱 163 smtp授权码
        self.to_addr = ["..."]  # 接收邮箱
        self.send_flag = False

    def process(self, message_context, receive_email_address, message_subject):
        smtp_server_host = "smtp.163.com"
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


if __name__ == "__main__":
    monitor = Monitor()
    monitor.process("fsdfsd", "...", "fsfsdf")
