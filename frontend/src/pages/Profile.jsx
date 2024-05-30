import React, {useState, useEffect} from 'react';
import '../App.css';
import '../styles/ModelKit.css'
import  "../styles/VehicleDetails.css";
import "../styles/Profile.css";
import {DefaultNavbar} from "../components/DefaultNavbar";
import Button from "@material-ui/core/Button";
import {Avatar, Divider} from "@material-ui/core";
import axios from "axios";
import {Link, useParams} from "react-router-dom";
import {TextField} from "@mui/material";

const API_ENDPOINT = process.env.REACT_APP_API_ENDPOINT;
function Profile()
{
    const { profileName } = useParams();
    const [profile, setProfile] = useState(null);
    const [isFollowed, setIsFollowed] = useState(false);
    const [loading, setLoading] = useState(true);
    const [editableDescription, setEditableDescription] = useState('');
    const [isSameUser, setIsSameUser] = useState(false);

    useEffect(() =>
    {
        setLoading(true);
        const fetchProfileData = async () =>
        {
            try
            {
                const profileResponse = await axios.get(`${API_ENDPOINT}/api/v1/profile/${profileName}`);
                setProfile(profileResponse.data);
                setIsFollowed(profileResponse.data.isFollowed === 'true');
                setEditableDescription(profileResponse.data.description);
                setIsSameUser(profileResponse.data.isSameUser === 'true');
            }
            catch (error)
            {
                console.error('Error fetching profile:', error);
            }
            finally
            {
                setLoading(false);
            }
        };

        fetchProfileData();
    }, [profileName]);

    const handleFollowButtonClick = async () =>
    {
        try
        {
            await axios.post(`${API_ENDPOINT}/api/v1/profile/follow/${profileName}`);
            setIsFollowed(!isFollowed);
        }
        catch (error)
        {
            console.error(`Error trying to ${isFollowed ? 'unfollow' : 'follow'}:`, error);
        }
    };

    const handleDescriptionChange = (event) =>
    {
        setEditableDescription(event.target.value);
    };

    const handleDescriptionSave = async () =>
    {
        try
        {
            await axios.put(`${API_ENDPOINT}/api/v1/profile/description`, { description: editableDescription});
            setEditableDescription(editableDescription);
        }
        catch (error)
        {
            console.error('Error saving description:', error);
        }
    };

    const handleDescriptionBlur = () =>
    {
        handleDescriptionSave();
    };

    if (loading)
        return <div>Loading...</div>;

    if (!profile)
        return <div>No profile found</div>;

    return (
        <div className="webpage">
            <DefaultNavbar></DefaultNavbar>
            <div className="content-space">
                <div className="content-space-info">
                    <div className="content-space-info-display-row">
                        <div className="profile">
                            <div className="profile-header">
                                <div className="profile-header-avatar">
                                    <div className="profile-header-avatar-container">
                                        <Avatar className="profile-header-avatar-entity">A</Avatar>
                                    </div>
                                </div>
                                <div className="profile-header-description">
                                    <div className="profile-header-description-top">
                                        <h1 id="user-profile-name">{profile.username}</h1>
                                        {isSameUser !== true && (
                                            <Button
                                                onClick={handleFollowButtonClick}
                                                class={isFollowed ? "unfollow-button" : "follow-button"}
                                                disableRipple
                                            >
                                                {isFollowed ? 'Unfollow' : 'Follow'}
                                            </Button>
                                        )}
                                    </div>
                                    <div className="profile-header-description-bottom">
                                        {isSameUser ? (
                                            <>
                                                <TextField
                                                    className="put-description"
                                                    variant="outlined"
                                                    value={editableDescription}
                                                    onChange={handleDescriptionChange}
                                                    onBlur={handleDescriptionBlur}
                                                    multiline
                                                    maxRows={2}
                                                />
                                            </>
                                        ) : (
                                            <p id="user-profile-description">{profile.description}</p>
                                        )}
                                    </div>
                                </div>
                            </div>
                            <Divider orientation="horizontal" variant="middle"/>
                            <div className="profile-desc">
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
                    </div>
                </div>
            </div>
        </div>
    )
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
export default Profile;