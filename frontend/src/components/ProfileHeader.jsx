import React from 'react';
import { Avatar, Button } from "@material-ui/core";

function ProfileHeader({ profile, isFollowed, handleFollowButtonClick })
{
    return (
        <div className="profile-header">
            <div className="profile-header-avatar">
                <div className="profile-header-avatar-container">
                    <Avatar className="profile-header-avatar-entity">A</Avatar>
                </div>
            </div>
            <div className="profile-header-description">
                <div className="profile-header-description-top">
                    <h1 id="user-profile-name">{profile.username}</h1>
                    {!profile.isSameUser && (
                        <Button
                            onClick={handleFollowButtonClick}
                            className={isFollowed ? "unfollow-button" : "follow-button"}
                            disableRipple
                        >
                            {isFollowed ? 'Unfollow' : 'Follow'}
                        </Button>
                    )}
                </div>
                <div className="profile-header-description-bottom">
                    <p id="user-profile-description">{profile.description}</p>
                </div>
            </div>
        </div>
    );
}

export default ProfileHeader;