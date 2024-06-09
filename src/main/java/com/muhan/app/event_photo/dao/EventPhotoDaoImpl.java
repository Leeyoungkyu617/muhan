package com.muhan.app.event_photo.dao;

import com.muhan.app.event_photo.domain.EventPhotoDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class EventPhotoDaoImpl implements EventPhotoDao {
    @Autowired
    SqlSession session;

    String namespace = "com.muhan.app.event_photo.dao.";

//    int photo_count;

    @Override
    public int photo_count(){
        return session.selectOne(namespace+"photo_count");
    }

    @Override
    public EventPhotoDto photo_select(int photo_seq) {
        System.out.println("*****DAO 시작 *****");
        return session.selectOne(namespace+"photo_select", photo_seq);
    }

    @Override
    public List<EventPhotoDto> photo_selectAll(Map map){
        System.out.println("Map:"+map);
        return session.selectList(namespace+"photo_selectAll", map);
    }

    @Override
    public int photo_deleteForAdmin(Integer photo_seq, Integer photo_uploader) {

        Map map = new HashMap();
        map.put("photo_seq", photo_seq);
        map.put("photo_uploader", photo_uploader);


        return session.delete(namespace+"photo_deleteForAdmin", map);
    }

    @Override
    public int photo_deleteAll() {
        return session.delete(namespace+"photo_deleteAll");
    }

    @Override
    public int photo_insert(EventPhotoDto photoDto) {

        return session.insert(namespace+"photo_insert", photoDto);
    }

    @Override
    public int photo_update(EventPhotoDto photoDto) {
        return session.update(namespace+"photo_update", photoDto);
    }








}
