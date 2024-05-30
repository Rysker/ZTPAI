import React, {useRef} from 'react';
import { Divider } from "@material-ui/core";
import ProfileHeader from "./ProfileHeader";
import {Link} from "react-router-dom";

function ProfileComponent({ profile, isFollowed, handleFollowButtonClick })
{
    const divRef = useRef(null);
    return (
        <div className="profile">
            <ProfileHeader
                profile={profile}
                isFollowed={isFollowed}
                handleFollowButtonClick={handleFollowButtonClick}
            />
            <Divider orientation="horizontal" variant="middle"/>
            <div className="profile-desc" ref={divRef}>
                <div className="profile-desc-collection">
                    <h1>Collection ({profile.profileCollection.count})</h1>
                    <div className="profile-desc-collection-gallery">
                        {profile.profileCollection.collectibles.map(collectible => (
                            <CollectibleEntity
                                key={collectible.order}
                                name={collectible.text}
                                photo={collectible.backgroundImage}
                            />
                        ))}
                    </div>
                    <Link to={profile.profileCollection.count > 0 ? `collection` : ''} style={{ textDecoration: 'none' }}>
                        {profile.profileCollection.count > 0 && <h2>+More</h2>}
                    </Link>
                </div>
                <div className="profile-desc-stats">
                    <div className="profile-desc-stats-container">
                        <h1>Reviews: {profile.reviewsCount}</h1>
                        <h1>Reputation: {profile.reputation}</h1>
                        <h1>Member since: {profile.memberSince}</h1>
                    </div>
                </div>
            </div>
        </div>
    );
}

function CollectibleEntity({name, photo})
{
    return(
        <div className="profile-desc-collection-entity">
            <img src={photo} alt={name + " photo"} />
            <div className="profile-desc-collection-entity-bar">
                {name}
            </div>
        </div>
    )
}

export default ProfileComponent;