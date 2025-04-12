# Threadbox evaluation

This repo contains three different case studies to evaluate Threadbox.

## Sandboxing a Flask web server

We use Threadbox to sandbox [flask-datta-able ](https://github.com/app-generator/flask-datta-able), an open-source Flask dashboard. We modified the dashboard to include a file-uploading feature and used PyYAML to parse the uploaded files. PyYAML had a command injection vulnerability (CVE-2017-18342). Threadbox enables developers to attach specific sandboxes to each request handler. We demonstrate how Threadbox can sandbox the server and prevent exploitation when a malicious file is uploaded.

https://github.com/user-attachments/assets/80acb279-aeb8-4a6c-8909-144697782924


## Sandboxing magic-wormhole

We use Threadbox to sandbox [magic-wormhole ](https://github.com/magic-wormhole/magic-wormhole), an open-source file-sharing Python program. We introduced a backdoor into magic-wormhole that allows attackers to execute arbitrary commands by sending files with specially formatted names. We demonstrate how Threadbox can sandbox individual functions—each with its own permissions—and how it can prevent backdoor exploitation.

https://github.com/user-attachments/assets/99b17886-b46f-4777-8600-54e9972afb2d

## Sandboxing a vulnerable PDF reader

We use Threadbox to sandbox a PDF reader that relies on a vulnerable version of [iText](https://github.com/itext/itext-java), a Java PDF parsing library. The library is vulnerable to XXE attacks (CVE-2017-9096). We demonstrate how Threadbox provides protection by sandboxing the PDF reader and uploading a malicious PDF file that exploits the vulnerability.

https://github.com/user-attachments/assets/7624a6f1-2f50-413f-bc08-22e0ecf1ef87



