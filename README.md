新浪私密博文备份
================
本程序用于备份新浪博客的*私密*博文。

运行环境
--------
本程序需要 Java 1.7 环境和 HtmlUnit 库。

已知问题
--------
* 本程序使用 java.io.Console 类的 readPassword() 方法读入密码，有可能无法在 Eclipse 下直接编译运行。请使用命令行方式单独运行。

* 因博客页面设置原因，程序中的格式解析部分可能无法正常工作。如有需要，请替换成相应的解析字符串。

* 新浪服务器有请求速率限制。如果出现程序中途退出的情况，请再次运行。本程序内建增量备份功能。

* 程序默认将博文备份到当前目录的 blogs.txt 文件。

其它说明
--------
* 本程序协议信息请参见 LICENSE 文件。

* “新浪”名称归属新浪公司所有。

- - -

SinaBackup
==========
SinaBackup is a tool to backup Sina *private* blogs.

Runtime Requirement
-------------------
SinaBackup needs Java 1.7 environment and HtmlUnit library.

Known Issues
------------
* SinaBackup uses readPassword() method in java.io.Console class to read in the password. This may not be supported by Eclipse. Please run SinaBackup under a terminal independently.

* Due to variation of the blog settings, parsing mechanism in this program may not work correctly. Please change the format strings accordingly, if necessary.

* Sina servers have limits on the accessing rate. If SinaBackup ternimates unexpectedly, please run the program again. SinaBackup has a built-in incremental backup mechanism.

* SinaBackup writes the blogs into blogs.txt under current folder.

Additional Information
----------------------
* Please refer to the file LICENSE to obtain license information about SinaBackup.

* "Sina" the name is the property of SINA Corporation.
