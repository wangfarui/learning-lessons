## 学习大纲

本次Git学习将全程按照[Git Book](https://git-scm.com/book/zh/v2)的章节进行学习。学习目录分为：

1. 起步
2. Git基础
3. Git分支
4. 服务器上的Git
5. 分布式Git
6. GitHub
7. Git工具
8. 自定义Git
9. Git与其他系统
10. Git内部原理

> 本次重新学习Git的初衷，也不是想要深入了解Git工作原理，掌握高端操作什么的。仅是希望在日后开发工作途中，坚持只使用Git命令行操作Git，理解每个Git命令的含义，能够轻松应付日常所需（例如克隆仓库、拉取代码、提交代码、远程推送、代码回退等）。除此之外，在学习途中将认为平常常使用的命令做一个归纳收集，便于后期温故。



## Git的自述

先说Git是什么，**Git是一个分布式版本控制系统（Distributed Version Control System，简称 DVCS）**。

而分布式版本控制系统是什么，就要说到为什么会出现版本控制系统这个东西。

### 本地版本控制

在早期，人习惯用复制整个项目目录的方式来保存不同的版本，或许还会改名加上备份时间以示区别。 这么做唯一的好处就是简单，但是特别容易犯错。 有时候会混淆所在的工作目录，一不小心会写错文件或者覆盖意想外的文件。

为了解决这个问题，人们很久以前就开发了许多种本地版本控制系统，大多都是**采用某种简单的数据库来记录文件的历次更新差异**。

其中最流行的一种叫做 RCS，现今许多计算机系统上都还看得到它的踪影。 RCS 的工作原理是在硬盘上保存补丁集（补丁是指文件修订前后的变化）；通过应用所有的补丁，可以重新计算出各个版本的文件内容。

**总结**：通过本地数据库记录项目文件的修改记录。缺点就是只能在单机实现版本控制。

### 集中化的版本控制系统

集中化的版本控制系统（Centralized Version Control Systems，简称 CVCS）应运而生。 这类系统，诸如 CVS、Subversion 以及 Perforce 等，都**有一个单一的集中管理的服务器，保存所有文件的修订版本，而协同工作的人们都通过客户端连到这台服务器，取出最新的文件或者提交更新**。 多年以来，这已成为版本控制系统的标准做法。

**总结**：通过中央服务器作为远程版本控制系统。优点是开发者们可以通过与中央服务器的交互，知道协同工作人员的修改记录，即可以多人协同工作。缺点是太依赖中央服务器，如果中央服务器出现问题，那么所有人都无法协同。

### 分布式版本控制系统

于是分布式版本控制系统（Distributed Version Control System，简称 DVCS）面世了。 在这类系统中，像 Git、Mercurial、Bazaar 以及 Darcs 等，**客户端并不只提取最新版本的文件快照， 而是把代码仓库完整地镜像下来，包括完整的历史记录**。 这么一来，任何一处协同工作用的服务器发生故障，事后都可以用任何一个镜像出来的本地仓库恢复。 因为每一次的克隆操作，实际上都是一次对代码仓库的完整备份。

**总结**：在集中化的版本控制系统基础上，将客户端也作为了完整的版本控制系统。优点是即使中央服务器挂了，也可以通过客户端还原所有版本记录，并且在项目文件发生变更做提交操作时可以先在本地提交，等中央服务器好了再同步。



## Git的基本介绍

* Git在对待文件的版本控制上，是通过记录快照的方式，而非差异比较。

* Git的命令操作，绝大多数都是本地执行。

* Git保证完整性，即在Git工作目录下的文件，Git会计算校验和（SHA-1 散列）。

* Git一般只添加数据，即Git的所有操作都是做增量记录，使得Git可以让工作目录回溯到以前的任何一个时间段。

* Git项目包含三个组件：工作区、暂存区、存储库。

    * 工作区是对项目的某个版本独立提取出来的内容。 这些从 Git 仓库的压缩数据库中提取出来的文件，放在磁盘上供你使用或修改。
    * 暂存区是一个文件，保存了下次将要提交的文件列表信息，一般在 Git 仓库目录中。 按照 Git 的术语叫做“索引”，不过一般说法还是叫“暂存区”。
    * Git 仓库目录（存储库）是 Git 用来保存项目的元数据和对象数据库的地方。 这是 Git 中最重要的部分，从其它计算机克隆仓库时，复制的就是这里的数据。

* Git管理的文件有三种状态：已修改、已暂存、已提交。

    - 已修改表示修改了文件，但还没保存到数据库中。

    - 已暂存表示对一个已修改文件的当前版本做了标记，使之包含在下次提交的快照中。

    - 已提交表示数据已经安全地保存在本地数据库中。

* Git的基本工作流程：

    1. 在工作区中修改文件。
    2. 将你想要下次提交的更改选择性地暂存，这样只会将更改的部分添加到暂存区。
    3. 提交更新，找到暂存区的文件，将快照永久性存储到存储库中。



## 安装Git

Git作为实现版本控制系统的软件，需要安装在本地环境。安装的Git版本尽量安装最新版本，因为Git对向后兼容做的比较好，在日常使用中不用担心版本兼容问题。

### Windows环境安装Git

打开下面的官方下载地址，选择对应的操作系统安装即可。

[https://git-scm.com/download/win](https://git-scm.com/download/win)

### macOS环境安装Git

```shell
brew install git
```

### Linux环境安装Git

打开下面的官方下载地址，选择对应Linux发行版的安装命令执行即可。

[https://git-scm.com/download/linux](https://git-scm.com/download/linux)



## Git环境配置

在安装完Git后，可以定制一些全局配置，例如user.name、user.email等。

Git 自带一个 `git config` 的工具来帮助设置控制 Git 外观和行为的配置变量。 这些变量存储在三个不同的位置：

1. `/etc/gitconfig` 文件: 包含系统上每一个用户及他们仓库的通用配置。 如果在执行 `git config` 时带上 `--system` 选项，那么它就会读写该文件中的配置变量。 （由于它是系统配置文件，因此你需要管理员或超级用户权限来修改它。）
2. `~/.gitconfig` 或 `~/.config/git/config` 文件：只针对当前用户。 你可以传递 `--global` 选项让 Git 读写此文件，这会对你系统上 **所有** 的仓库生效。
3. 当前使用仓库的 Git 目录中的 `config` 文件（即 `.git/config`）：针对该仓库。 你可以传递 `--local` 选项让 Git 强制读写此文件，虽然默认情况下用的就是它。。 （当然，你需要进入某个 Git 仓库中才能让该选项生效。）

每一个级别会覆盖上一级别的配置，所以 `.git/config` 的配置变量会覆盖 `/etc/gitconfig` 中的配置变量。

### 用户信息

安装完 Git 之后，要做的第一件事就是设置你的用户名和邮件地址。 这一点很重要，因为每一个 Git 提交都会使用这些信息，它们会写入到你的每一次提交中，不可更改：

```shell
$ git config --global user.name "Wray"
$ git config --global user.email wray@example.com
```

再次强调，如果使用了 `--global` 选项，那么该命令只需要运行一次，因为之后无论你在该系统上做任何事情， Git 都会使用那些信息。 当你想针对特定项目使用不同的用户名称与邮件地址时，可以在那个项目目录下运行没有 `--global` 选项的命令来配置。

### 检查配置信息

如果想要检查你的配置，可以使用 `git config --list` 命令来列出所有 Git 当时能找到的配置。

```shell
$ git config --list
user.name=John Doe
user.email=johndoe@example.com
color.status=auto
color.branch=auto
color.interactive=auto
color.diff=auto
...
```

你可能会看到重复的变量名，因为 Git 会从不同的文件中读取同一个配置（例如：`/etc/gitconfig` 与 `~/.gitconfig`）。 这种情况下，Git 会使用它找到的每一个变量的最后一个配置。

你可以通过输入 `git config <key>`： 来检查 Git 的某一项配置

（需要注意的是，git config读取配置也是按照配置文件的优先级读取的）

```shell
$ git config user.name
Wray
```

由于 Git 会从多个文件中读取同一配置变量的不同值，因此你可能会在其中看到意料之外的值而不知道为什么。 此时，你可以查询 Git 中该变量的 **原始** 值（也就是最终实际用到的值），它会告诉你哪一个配置文件最后设置了该值：

```shell
$ git config --show-origin user.name
file:/Users/wangfarui/.gitconfig	Wray
```



## Git的Help

熟练使用git help，基本可以找到你想要的所有git命令语法。

使用 Git 时需要获取帮助，有三种等价的方法可以找到 Git 命令的综合手册（manpage）：

```shell
$ git help <verb>
$ git <verb> --help
$ man git-<verb>
```

其中的`<verb>`表示git动作，例如config、add、commit等。如果不知道有哪些verb，可以输入`git help -a`，查看git的所有命令。

例如想要获取`git config`命令的手册，执行：

```shell
$ git help config
```

输出结果如下：

![image-20231013170115289](git-study-1/image-20231013170115289.png)

其中的命令语法参数，我将其大致分为 **固定字符**、**动态字符**、**可选字符**。

* 固定字符：例如 `git` 、`config`、`--get-all`、`--type`、`-z`等。
* 动态字符：由`<>`括起来的参数就表示动态字符。例如`<file-option>`指输入指定配置文件名、`<name>`指config的参数名。
* 可选字符：由`[]`括起来的参数就表示可选字符，它不是必须输入的。例如`[file-option]`不指定时，git会配置文件的优先级选择对应的配置文件。

<br>

使用`git config <verb>`获取的是全面的手册，如果只需要可用选项的快速参考，那么可以用 `-h` 选项获得更简明的帮助文档。

```shell
$ git config -h
usage: git config [<options>]

Config file location
    --global              use global config file
    --system              use system config file
    --local               use repository config file
    --worktree            use per-worktree config file
    -f, --file <file>     use given config file
    --blob <blob-id>      read config from given blob object

Action
    --get                 get value: name [value-pattern]
    --get-all             get all values: key [value-pattern]
    --get-regexp          get values for regexp: name-regex [value-pattern]
    --get-urlmatch        get value specific for the URL: section[.var] URL
    --replace-all         replace all matching variables: name value [value-pattern]
    --add                 add a new variable: name value
    --unset               remove a variable: name [value-pattern]
    --unset-all           remove all matches: name [value-pattern]
    --rename-section      rename section: old-name new-name
    --remove-section      remove a section: name
    -l, --list            list all
    --fixed-value         use string equality when comparing values to 'value-pattern'
    -e, --edit            open an editor
    --get-color           find the color configured: slot [default]
    --get-colorbool       find the color setting: slot [stdout-is-tty]

Type
    -t, --type <type>     value is given this type
    --bool                value is "true" or "false"
    --int                 value is decimal number
    --bool-or-int         value is --bool or --int
    --bool-or-str         value is --bool or string
    --path                value is a path (file or directory name)
    --expiry-date         value is an expiry date

Other
    -z, --null            terminate values with NUL byte
    --name-only           show variable names only
    --includes            respect include directives on lookup
    --show-origin         show origin of config (file, standard input, blob, command line)
    --show-scope          show scope of config (worktree, local, global, system, command)
    --default <value>     with --get, use default value when missing entry
```

