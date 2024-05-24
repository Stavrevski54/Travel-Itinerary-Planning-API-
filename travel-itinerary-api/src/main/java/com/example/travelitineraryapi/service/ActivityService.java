package com.example.travelitineraryapi.service;

import com.example.travelitineraryapi.entity.Activity;
import com.example.travelitineraryapi.repository.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ActivityService {

    @Autowired
    private ActivityRepository activityRepository;

    public Activity createActivity(Activity activity) {
        return activityRepository.save(activity);
    }

    public List<Activity> getAllActivities() {
        return activityRepository.findAll();
    }

    public Optional<Activity> getActivityById(Long id) {
        return activityRepository.findById(id);
    }

    public Activity updateActivity(Long id, Activity activityDetails) {
        Optional<Activity> optionalActivity = activityRepository.findById(id);
        if (optionalActivity.isPresent()) {
            Activity activity = optionalActivity.get();
            activity.setName(activityDetails.getName());
            activity.setDescription(activityDetails.getDescription());
            activity.setDate(activityDetails.getDate());
            return activityRepository.save(activity);
        } else {
            throw new RuntimeException("Activity not found with id " + id);
        }
    }

    public void deleteActivity(Long id) {
        activityRepository.deleteById(id);
    }
}
