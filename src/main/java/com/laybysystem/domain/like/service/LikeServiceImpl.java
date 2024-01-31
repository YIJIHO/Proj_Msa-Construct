package com.laybysystem.domain.like.service;

import com.laybysystem.domain.like.dto.LikeDTO;
import com.laybysystem.domain.like.mapper.LikeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService {
    public final LikeMapper likemapper;

    @Override
    public Boolean commonChangeLikeSet(int likeCreatorSeq,int contentType,int contentSeq,int contentProvider,int changeType){
        boolean returnValue = false;
        //1: 생성타입, 2: 삭제타입
        switch (changeType){
            case 1:
                LikeDTO createLike = new LikeDTO();
                createLike.setLikeCreatorSeq(likeCreatorSeq);
                createLike.setContentType(contentType);
                createLike.setContentSeq(contentSeq);
                createLike.setContentProvider(contentProvider);
                createLike = likemapper.changeLike(createLike);
                if(createLike!=null){
                    returnValue = true;
                } else {
                    returnValue = false;
                }
                break;
            case 2:
                LikeDTO deleteLike = new LikeDTO();
                deleteLike.setLikeCreatorSeq(likeCreatorSeq);
                deleteLike.setContentType(contentType);
                deleteLike.setContentSeq(contentSeq);
                deleteLike = likemapper.changeLike(deleteLike);
                if(deleteLike==null){
                    returnValue = true;
                } else {
                    returnValue = false;
                }
                break;
        }
        return returnValue;
    }
}
