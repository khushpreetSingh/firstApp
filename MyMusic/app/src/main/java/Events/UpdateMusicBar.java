package Events;

/**
 * Created by khushpreetsingh on 06/08/15.
 */
public class UpdateMusicBar {
    public String songname;
    public String songalbum;
    public String songImageUrl;

    public UpdateMusicBar(String songname, String songalbum,String songImageUrl) {
        this.songname = songname;
        this.songalbum = songalbum;
        this.songImageUrl=songImageUrl;
    }
}