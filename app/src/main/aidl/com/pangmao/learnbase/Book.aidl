//第一类AIDL文件
//这个文件的作用是引入了一个序列化对象 Student 供其他的AIDL文件使用
//注意：Student.aidl与Book.java的包名应当是一样的
package com.pangmao.learnbase;

//注意parcelable是小写
parcelable Book ;
