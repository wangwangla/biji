## AIDI的使用

音乐播放器可以在service中播放

1.写一个MusicPlayerService继承service，把常用方法写出来，但是不实现他们。

2.参考服务方法，可以写对应的AIDI文件

3.把服务在功能清单里面注册

4.吧AIDI，文件生成类，在服务中绑定

5.绑定方式启动service

6.在MusicPalyerService得到列表

7.当绑定成功时播放



### 实现

创建一个service

```
public class MusicService extends Service {
    private List<Song> arrayList;
    private int position;
    private Song mediaItem;
    //用于部分音乐
    private MediaPlayer mediaPlayer;

    @Override
    public void onCreate() {
        super.onCreate();
        //服务创建的时候加载音乐列表
        arrayList = SongDataUtils.musicList;
    }
    
    //绑定服务使用，一定是需要这个方法的
    @Override
    public IBinder onBind(Intent intent) {
        return stub;
    }
//////////////////////////为了写AiDI使用/////////////////////////
	//定义一些常用方法
	public void start(){
        
	}
	……
/////////////////////////////////////////////////////////////////
	
	//
    private IMyAidlInterface.Stub stub = new IMyAidlInterface.Stub() {
        private MusicService musicPlayService=MusicService.this;
        @Override
        public void openAudio(int position) throws RemoteException {
            musicPlayService.openAudio(position);
        }

        @Override
        public void start() throws RemoteException {
            musicPlayService.start();
        }

        @Override
        public void pause() throws RemoteException {
            musicPlayService.pause();
        }

        @Override
        public void stop() throws RemoteException {
            musicPlayService.stop();
        }

        @Override
        public int getCurrentPosition() throws RemoteException {
            return musicPlayService.getCurrentPosition();
        }

        @Override
        public int getDuration() throws RemoteException {
            return musicPlayService.getDuration();
        }

        @Override
        public String getArtitst() throws RemoteException {
            return musicPlayService.getArtitst();
        }

        @Override
        public String getName() throws RemoteException {
            return musicPlayService.getName();
        }

        @Override
        public String getAudioPath() throws RemoteException {
            return musicPlayService.getAudioPath();
        }

        @Override
        public void next() throws RemoteException {
            musicPlayService.next();
        }

        @Override
        public void pre() throws RemoteException {
            musicPlayService.pre();
        }

        @Override
        public void setPlayMode(int playMode) throws RemoteException {
            musicPlayService.setPlayMode(playMode);
        }

        @Override
        public int getPlayMode() throws RemoteException {
            return musicPlayService.getPlayMode();
        }
    };

}

```

上面服务已经创建出来了。

- 写aidi文件

步骤：（1）右击app，然后创建aidi

（2）将服务中的方法都复制到这个里面。

```
interface IMyAidlInterface {

         /**
            *  常用方法
            */
           /**
            * 打开音频
            * @param position
            */
           void openAudio(int position);

           /**
            * 播放
            */
           void start();
           /**
            * 暂停
            */
           void pause();
           /**
            * 停止
            */
           void stop();
           /**
            * 播放进度
            */
           int getCurrentPosition();
           /**
            * 当前的时长
            */
           int getDuration();

           /**
            * 得到艺术家
            * @return
            */
           String getArtitst();
           /**
            * 得到名字
            * @return
            */
           String getName();
           /**
            * 得到歌曲路径
            * @return
            */
           String getAudioPath();

           void next();

           void pre();

           //播放模式
           void setPlayMode(int playMode);
           //播放模式
           int getPlayMode();
   }

```

build一下就会在build/genera/source/aidi……下创建出一个类

- 注册服务

```
 <service android:name=".service.MusicService">
        <intent-filter>
            <action android:name="kw.test.demo.model_OPEN"></action>
        </intent-filter>
    </service>
```

- 在service中使用

```
 private IMyAidlInterface.Stub stub = new IMyAidlInterface.Stub() {
        private MusicService musicPlayService=MusicService.this;
        @Override
        public void openAudio(int position) throws RemoteException {
            musicPlayService.openAudio(position);
        }

        @Override
        public void start() throws RemoteException {
            musicPlayService.start();
        }

        @Override
        public void pause() throws RemoteException {
            musicPlayService.pause();
        }

        @Override
        public void stop() throws RemoteException {
            musicPlayService.stop();
        }

        @Override
        public int getCurrentPosition() throws RemoteException {
            return musicPlayService.getCurrentPosition();
        }

        @Override
        public int getDuration() throws RemoteException {
            return musicPlayService.getDuration();
        }

        @Override
        public String getArtitst() throws RemoteException {
            return musicPlayService.getArtitst();
        }

        @Override
        public String getName() throws RemoteException {
            return musicPlayService.getName();
        }

        @Override
        public String getAudioPath() throws RemoteException {
            return musicPlayService.getAudioPath();
        }

        @Override
        public void next() throws RemoteException {
            musicPlayService.next();
        }

        @Override
        public void pre() throws RemoteException {
            musicPlayService.pre();
        }

        @Override
        public void setPlayMode(int playMode) throws RemoteException {
            musicPlayService.setPlayMode(playMode);
        }

        @Override
        public int getPlayMode() throws RemoteException {
            return musicPlayService.getPlayMode();
        }
    };
    
    最重要的一点就是将其返回去
    //绑定服务使用，一定是需要这个方法的
    @Override
    public IBinder onBind(Intent intent) {
        return stub;
    }
```



- 使用这个内部类来调用服务，也就是上面方法的实现

- 我们绑定服务

  ```
  Intent intent = new Intent();
  bindService(intent,con，上下文，状态)
   Intent intent = new Intent(MainActivity.this, MusicService.class);
          //设置动作
          intent.setAction("kw.test.demo.model_OPEN");
          //回调con
          bindService(intent, con, Context.BIND_AUTO_CREATE);
  
  创建连接
   private ServiceConnection con = new ServiceConnection() {
          @Override
          public void onServiceConnected(ComponentName name, IBinder service) {
              iMusicPlayService = IMyAidlInterface.Stub.asInterface(service);
              if (iMusicPlayService != null) {
                  try {
                      iMusicPlayService.openAudio(position);
                  } catch (RemoteException e) {
                      e.printStackTrace();
                  }
              }
          }
          @Override
          public void onServiceDisconnected(ComponentName name) {
              try {
                  if (iMusicPlayService != null) {
                      iMusicPlayService.stop();
                      iMusicPlayService = null;
                  }
              } catch (RemoteException e) {
                  e.printStackTrace();
              }
          }
  ```

它的调用过程是：创建了aidi，让通过实现服务中的方法，在aidi中去调用服务中的方法





使用工具创建一个aidi

```
// IMyAidlInterface.aidl
package test.kw.music;

// Declare any non-default types here with import statements

interface IMyAidlInterface {

         /**
            *  常用方法
            */
           /**
            * 打开音频
            * @param position
            */
           void openAudio(int position);

           /**
            * 播放
            */
           void start();
           /**
            * 暂停
            */
           void pause();
           /**
            * 停止
            */
           void stop();
           /**
            * 播放进度
            */
           int getCurrentPosition();
           /**
            * 当前的时长
            */
           int getDuration();

           /**
            * 得到艺术家
            * @return
            */
           String getArtitst();
           /**
            * 得到名字
            * @return
            */
           String getName();
           /**
            * 得到歌曲路径
            * @return
            */
           String getAudioPath();

           void next();

           void pre();

           //播放模式
           void setPlayMode(int playMode);
           //播放模式
           int getPlayMode();
   }
```

