package in.hermansyah.temanbuku;

import android.os.Parcel;
import android.os.Parcelable;

public class Buku implements Parcelable{
    private String nama, author, sinopsis, gambar;

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.nama);
        dest.writeString(this.author);
        dest.writeString(this.sinopsis);
        dest.writeString(this.gambar);
    }

    public Buku() {
    }

    protected Buku(Parcel parcel) {
        this.nama = parcel.readString();
        this.author = parcel.readString();
        this.sinopsis = parcel.readString();
        this.gambar = parcel.readString();
    }

    public static final Parcelable.Creator<Buku> CREATOR = new Parcelable.Creator<Buku>() {

        @Override
        public Buku createFromParcel(Parcel source) {
            return new Buku(source);
        }

        @Override
        public Buku[] newArray(int size) {
            return new Buku[size];
        }
    };
}
