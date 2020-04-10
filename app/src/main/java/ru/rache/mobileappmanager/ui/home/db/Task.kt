package ru.rache.mobileappmanager.ui.home.db

import android.os.Parcel
import android.os.Parcelable

class Task : Parcelable {
    var id: Int = 0
    var text: String = ""

    constructor(){
    }

    constructor(id: Int, text: String) {
        this.id = id
        this.text = text
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(text)
    }

    override fun describeContents(): Int {
        return 0
    }

    protected constructor(`in`: Parcel){
        this.id = `in`.readInt()
        this.text = `in`.readString().toString()
    }

    override fun toString(): String {
        return "Task(id=$id, text=$text)"
    }

    companion object{
        @JvmField val CREATOR: Parcelable.Creator<Task> = object : Parcelable.Creator<Task>{
            override fun createFromParcel(source: Parcel): Task {
                return Task(source)
            }

            override fun newArray(size: Int): Array<Task?> {
                return arrayOfNulls(size)
            }
        }
    }
}
