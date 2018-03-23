#如果主dex不包含LoadDexsActivity类和内部类的话，一起动的时候就崩溃，可以加以下这些代码把他们保留在主dex。
#备注：如果代码被混淆的话需要更改的类名
-keep class com.songag.loadmultipledexs.LoadDexsActivity { *; }
-keep class com.songag.loadmultipledexs.LoadDexsActivity$1 { *; }
-keep class com.songag.loadmultipledexs.LoadDexsActivity$1$1 { *; }
