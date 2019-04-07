package com.pangmao.learnbase.aidl;

import android.os.Parcel;
import android.os.Parcelable;

public class Book implements Parcelable {
    private int price;
    private String bookName;

    private Book(Parcel in) {
        this.price = in.readInt();
        this.bookName = in.readString();
    }

    public Book() {
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.price);
        dest.writeString(this.bookName);
    }

    /**
     * 参数是一个Parcel,用它来存储与传输数据
     * 实现此方法后对象在AIDL文件里就可以用 out 或者 inout 来作为它的定向 tag 了
     */
    public void readFromParcel(Parcel dest) {
        //注意，此处的读值顺序应当是和writeToParcel()方法中一致的
        price = dest.readInt();
        bookName = dest.readString();
    }

    public static final Parcelable.Creator<Book> CREATOR = new Parcelable.Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel source) {
            return new Book(source);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

    @Override
    public String toString() {
        return "Book{" +
                "price=" + price +
                ", bookName='" + bookName + '\'' +
                '}';
    }
}
