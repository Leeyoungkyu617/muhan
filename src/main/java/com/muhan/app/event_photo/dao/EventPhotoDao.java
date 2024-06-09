package com.muhan.app.event_photo.dao;

import com.muhan.app.event_photo.domain.EventPhotoDto;

import java.util.List;
import java.util.Map;

public interface EventPhotoDao {

    int photo_count();

    EventPhotoDto photo_select(int photo_seq);

    List<EventPhotoDto> photo_selectAll(Map map);

    int photo_deleteForAdmin(Integer photo_seq, Integer photo_uploader);

    int photo_deleteAll();

    int photo_insert(EventPhotoDto photoDto);

    int photo_update(EventPhotoDto photoDto);
}
