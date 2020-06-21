package in.hermansyah.temanbuku;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class BukuData{
    public static String[][] data = new String[][]{
            {"How They Started Digital ", "David Lester",
                    "Bringing to life the stories behind the world's most successful " +
                            "digital businesses, 'How They Started Digital' showcases " +
                            "profiles from gaming companies to social media brands. " +
                            "Giving insight and inspiration, each profile reveals " +
                            "how each idea originated and became the success it is today.", "https://images.gr-assets.com/books/1356493763l/14611864.jpg"},
            {"Dream Catcher", "Alanda Kariza", "Mimpi itu kebutuhan. Layaknya udara, tanpa disadari, aku, kamu, dan kita semua membutuhkan mimpi. Mimpilah yang menuntun kita atas apa yang kita kerjakan saat ini karena hari ini adalah jawaban atas mimpi kita tempo hari.\n" +
                    "DreamCatcher memberikan gambaran tentang bagaimana merancang mimpi. Inilah rancangan hidup yang kita coba reka sendiri. Alanda Kariza berbagi hal-hal yang bisa kita lakukan untuk menciptakan mimpi dan meraihnya sejak dini. Memanfaatkan kekurangan, meningkatkan produktivitas, dan berbagi dengan orang lain adalah beberapa di antaranya.\n" +
                    "Buku ini dilengkapi lembar aktivitas untuk mencatat hal-hal yang ingin kita capai. Tak ketinggalan, ada pula profil para sosok muda yang sukses mewujudkan impian.\n" +
                    "So, live your dreams. Hidup yang dipenuhi mimpi akan banyak bercerita tentang masa depan.", "https://images.gr-assets.com/books/1334366340l/13601021.jpg"},
            {"Misteri DNA ", "Kazuo Murakami", "Manusia tersusun dari banyak sel, dan di dalam sel " +
                    "tertulis kode rahasia yang luar biasa banyaknya. Penguraian seluruh kode DNA " +
                    "manusia masih berlangsung dan diperkirakan kita akan bisa memahami gambaran " +
                    "keseluruhan konstruksi dasar kehidupan jika proses ini telah rampung.","https://images.gr-assets.com/books/1363836809l/17665401.jpg"},
            {"Think Like A Freak", " Steven D. Levitt dan Stephen J. Dubner ", "Buku ini memaparkan pemikiran yang tidak biasa, aneh, dan jarang terpikir oleh orang kebanyakan. Disertai contoh-contoh menarik, cerita yang memikat, dan analisis yang tidak lazim, kedua penulis mendorong kita agar mampu berpikir jauh lebih rasional, lebih kreatif, dan lebih produktif. ","https://images.gr-assets.com/books/1455783713l/29216441.jpg"},
            {"The Guest Book", "Sarah Blake", "An unforgettable love story, a novel about past mistakes and betrayals that ripple throughout generations, The Guest Book examines not just a privileged American family, but a privileged America. It is a literary triumph. ","https://images.gr-assets.com/books/1538159371l/41138424.jpg"}
    };

    public static ArrayList<Buku> getListData(){
        Buku buku = null;
        ArrayList<Buku> list = new ArrayList<>();
        for (String[] aData: data){
            buku = new Buku();
            buku.setNama(aData[0]);
            buku.setAuthor(aData[1]);
            buku.setSinopsis(aData[2]);
            buku.setGambar(aData[3]);
            list.add(buku);
        }

        return list;
    }
}
