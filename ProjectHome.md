This project aims to provide a command line tool which provides a simple and easy way to backup google contacts.

After crashed my own google contacts on my android, I decided to create this project in order to create a cron job which save all my google contacts daily.

---

# The export #
## Datas ##
I only implemented an export of that following datas for each contact

  * full name
  * phone numbers
  * email addresses

On this first release, datas are exported only on a xml document. I planned to create an export of a CSV file.
## A Sample ##
```
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<gContactRoot>
<contacts>
<emails>fisrtmail@mail.net</emails>
<fullname>First John Doe</fullname>
</contacts>
<contacts>
<emails>firstmail@mail.net</emails>
<fullname>Second John Doe</fullname>
</contacts>
...
<gContactRoot>
```

# How to Install #
## Prerequisites ##
You must have a JDK ( at least 6) installed on your computer and ... a google account :)

## Setup ##

Download the latest zip from the download tab and unzip .

# How to use #
In the distro, you could find two scripts for window$ and gnu/linux

You could run this utility on Window$ as following
```
run.bat -u john.doe@gmail.com -p pass -d c:\test
```

On GNU/Linux, you could test as this
```
run.sh -u john.doe@gmail.com -p pass -d ~ 
```


By the way, you could configure a http proxy by these options -ph -pu

Example

```
run.bat -ph host -pp 8080 -u john.doe@gmail.com -p pass -d c:\test
```