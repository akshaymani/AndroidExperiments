package com.example.newsreader;

import android.os.Parcel;
import android.os.Parcelable;

public class NewsItem implements Parcelable{

	// We can use Serializable can also be used, but it is half as slow as Parcelable
	public static Parcelable.Creator<NewsItem> CREATOR = new Parcelable.Creator<NewsItem>() {

		@Override
		public NewsItem createFromParcel(Parcel source) {
			NewsItem item = new NewsItem();
			item.title = source.readString();
			item.link = source.readString();
			return item;
		}

		@Override
		public NewsItem[] newArray(int size) {
			return new NewsItem[size];
		}
	};
	
	public String title;
	public String link;
	
	@Override
	public int describeContents() {
		return 0;
	}
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(title);
		dest.writeString(link);
	}
	
	@Override
	public String toString() {
		return title;
	}

}
