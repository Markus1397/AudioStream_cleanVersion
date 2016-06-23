package at.audiostream.client.backend;

import java.util.logging.Level;
import java.util.logging.Logger;
import uk.co.caprica.vlcj.component.AudioMediaPlayerComponent;
import uk.co.caprica.vlcj.player.MediaPlayer;
import uk.co.caprica.vlcj.player.MediaPlayerEventAdapter;
import uk.co.caprica.vlcj.binding.LibVlc;
import uk.co.caprica.vlcj.discovery.NativeDiscovery;

public class RTPClient {

   private final AudioMediaPlayerComponent mediaPlayerComponent;

    public MediaPlayer getMediaPlayer() {
        return mediaPlayerComponent.getMediaPlayer();
    }

   public static void main(String[] args) throws InterruptedException {
       boolean found = new NativeDiscovery().discover();
       System.out.println(found);
       System.out.println(LibVlc.INSTANCE.libvlc_get_version());

       RTPClient client= new RTPClient();
       client.start(args[0]);
       try {
           Thread.currentThread().join();
       }
       catch(InterruptedException e) {
       }
      
   }

   private RTPClient() {
       mediaPlayerComponent = new AudioMediaPlayerComponent();
       mediaPlayerComponent.getMediaPlayer().addMediaPlayerEventListener(new MediaPlayerEventAdapter() {
           @Override
           public void stopped(MediaPlayer mediaPlayer) {
               exit(0);
           }

           @Override
           public void finished(MediaPlayer mediaPlayer) {
               exit(0);
           }

           @Override
           public void error(MediaPlayer mediaPlayer) {
               exit(1);
           }

           @Override
           public void playing(MediaPlayer mediaPlayer) {
               System.out.println(mediaPlayer.getTime());
               super.playing(mediaPlayer); //To change body of generated methods, choose Tools | Templates.
           }
           
       });
   }

   private void start(String mrl) {
       mediaPlayerComponent.getMediaPlayer().playMedia(mrl);
       
   }

   private void exit(int result) {
       mediaPlayerComponent.release();
       System.exit(result);

   }
   private class TimePrinter implements Runnable{
       
       private MediaPlayer player;
       public TimePrinter(MediaPlayer player){
           this.player = player;
       }

        @Override
        public void run() {
            while(true){
                System.out.println(player.getTime());
                System.out.println(player.getLength());
                System.out.println(player.getMediaDetails().toString());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(RTPClient.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
   }
}
