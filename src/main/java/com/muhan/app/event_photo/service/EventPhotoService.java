package com.muhan.app.event_photo.service;

import com.muhan.app.event_photo.dao.EventPhotoDao;
import com.muhan.app.event_photo.domain.EventPhotoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class EventPhotoService {

    @Autowired
    EventPhotoDao photoDao;

    public int getPhotoCount() {
        return photoDao.photo_count();
    }

    public EventPhotoDto read(Integer photo_seq) {
        EventPhotoDto photoDto = photoDao.photo_select(photo_seq);
        return photoDto;
    }

    public List<EventPhotoDto> getList(Map map) {
        System.out.println("map2222:" + map);
        System.out.println("photo_selectAll:" + photoDao.photo_selectAll(map));
        return photoDao.photo_selectAll(map);
    }

    public int remove(Integer photo_seq, Integer photo_uploader) {
        return photoDao.photo_deleteForAdmin(photo_seq, photo_uploader);
    }

    public int removeAll() {
        return photoDao.photo_deleteAll();
    }


    public int write(EventPhotoDto photoDto) {
        return photoDao.photo_insert(photoDto);
    }

    public int update(EventPhotoDto photoDto) {
        return photoDao.photo_update(photoDto);
    }
}
