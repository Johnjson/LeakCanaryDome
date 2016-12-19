# LeakCanaryDome
快要过年了，项目也没有那么赶了。最近稍微有点时间，老大说项目中的oom比较严重，所以现在整理一下项目中遇到的oom了。
说到oom，就不得不说它产生的原因：
1.数据库的cursor没有关闭。
2.构造adapter没有使用缓存contentview。
3.调用registerReceiver()后未调用unregisterReceiver().
4.未关闭InputStream/OutputStream。
5.Bitmap使用后未调用recycle()。
6.Context泄漏。
7.static关键字
8.Handle泄露等。
其实oom与我们的息息相关，杜绝他发生的办法就是，细心细心再细心！
说了这么多 现在该说我们 内存泄露检测神器 LeakCanary
LeakCanary是Square公司推出的内存泄露检测工具，其实Square公司对开源的贡献还是挺大的 就Android而言 比如：
picasso https://github.com/square/picasso 图片开源框架
retrofit https://github.com/square/retrofit
okhttp https://github.com/square/okhttp 很好用的网络框架
等等；
今天我们只说 LeakCanary https://github.com/square/leakcanary 这是源码地址，大家有时间也可以看看研究一下
现在 就开始我们的引用工作了
LeakCanary现在最新的版本是1.5


FE4B43DF-0466-4478-8D13-541D796C1445.png

C60606D8-D874-483D-A533-40E0F88095CB.png

最后记着一定要注册Application


B4726F18-9277-4345-AC66-3A5DBDCEE933.png

现在来看项目 下面我写了一个小的demo 自己手动造成两个oom产生的原因


device-2016-12-19-153938.png

代码如下：


D2E64907-3A93-49A9-875E-A788F56B01B9.png

device-2016-12-19-154257.png
下边这个是handle造成的内存泄露 ：activity关闭但是 handle 并没有释放掉


device-2016-12-19-154348.png
最后这个才是activity引起的内存泄漏： Activity因为持有静态变量，生命周期也不能正常执行 所以GC不会将其清理


852227-20161012100609234-1023375460.png
项目地址 ：https://github.com/Johnjson/LeakCanaryDome
参考资料：
http://www.cnblogs.com/Fndroid/p/5951740.html
