package com.social.socialapp.services;


import com.social.socialapp.entity.UserEntity;
import com.social.socialapp.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


// Service to add friends
@Service
public class FriendService {

    @Autowired
    private UserRepo userRepo;

    public void addFriend(int userId, String friendId) {
        UserEntity user = userRepo.findById(userId).orElseThrow();
        UserEntity friend = userRepo.findByUsername(friendId).orElseThrow();
        user.getFriends().add(friendId);
        userRepo.save(user);
    }

}
