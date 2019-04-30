package com.pangmao.learnbase.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

import com.pangmao.learnbase.Book;
import com.pangmao.learnbase.BookManager;
import com.pangmao.learnbase.util.LoggUtil;

import java.util.ArrayList;
import java.util.List;

public class AIDLService extends Service {
    public AIDLService() {
    }

    //包含Book对象的list
    private List<Book> mBooks = new ArrayList<>();

    //由AIDL文件生成的BookManager
    private final BookManager.Stub mBookManager = new BookManager.Stub() {
        @Override
        public List<Book> getBooks() throws RemoteException {
            synchronized (this) {
                LoggUtil.log("invoking getBooks() method , now the list is : " + mBooks.toString());
                if (mBooks != null) {
                    return mBooks;
                }
                return new ArrayList<>(10);
            }
        }


        @Override
        public void addBook(Book book) throws RemoteException {
            synchronized (this) {
                if (mBooks == null) {
                    mBooks = new ArrayList<>();
                }
                if (book == null) {
                    LoggUtil.log("Book is null in In");
                    book = new Book();
                }
                //尝试修改book的参数，主要是为了观察其到客户端的反馈
                book.setPrice(2333);
                if (!mBooks.contains(book)) {
                    mBooks.add(book);
                }
                //打印mBooks列表，观察客户端传过来的值
                LoggUtil.log("invoking addBooks() method , now the list is : " + mBooks.toString());
            }
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        Book book = new Book();
        book.setBookName("Android开发艺术探索");
        book.setPrice(28);
        mBooks.add(book);
    }

    @Override
    public IBinder onBind(Intent intent) {
        LoggUtil.log(String.format("on bind,intent = %s", intent.toString()));
        return mBookManager;
    }

}
