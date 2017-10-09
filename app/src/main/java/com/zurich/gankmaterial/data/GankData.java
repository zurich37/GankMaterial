package com.zurich.gankmaterial.data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by weixinfei on 2016/11/27.
 */
public class GankData implements Serializable{

    /**
     * _id : 5836a7fc421aa91cb7afe7e0
     * createdAt : 2016-11-24T16:42:36.919Z
     * desc : 支持https的ijkplayer播放器
     * images : ["http://img.gank.io/22aa7a50-de1f-4697-8eb8-7bcc247cce58"]
     * publishedAt : 2016-11-25T11:29:49.832Z
     * source : web
     * type : Android
     * url : https://github.com/l123456789jy/ijkplayer
     * used : true
     * who : Lazy
     */
    public String _id;
    public String createdAt;
    public String desc;
    public String publishedAt;
    public String source;
    public String type;
    public String url;
    public boolean used;
    public String who;
    public List<String> images;
}
