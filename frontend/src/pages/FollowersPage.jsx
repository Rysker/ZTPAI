import {Avatar} from "@material-ui/core";
import Button from "@material-ui/core/Button";
import React, {useEffect, useState} from "react";
import Webpage from "../components/Webpage";
import {DefaultNavbar} from "../components/DefaultNavbar";
import axios from "axios";
import {Link} from "react-router-dom";

const API_ENDPOINT = process.env.REACT_APP_API_ENDPOINT;
function FollowersPage({setError, setSuccess})
{
    const [followers, setFollowers] = useState([]);

    useEffect(() => {
        axios.get(`${API_ENDPOINT}/api/v1/followers`)
            .then(response => {
                setFollowers(response.data);
            })
            .catch(() => {
                setError('Failed to fetch followers.');
            });
    }, []);

    return (
        <Webpage className={"webpage"}>
            {({ setError, setSuccess }) => (
                <>
                    <DefaultNavbar/>
                    <div className="content-space-reports">
                        <div className="reports-place">
                            {followers.length > 0 ? (
                                followers.map((follower, index) => (
                                    <Profile
                                        key={follower.followerId}
                                        follower={follower}
                                        setError={setError}
                                        setSuccess={setSuccess}
                                        setFollowers={setFollowers}
                                    />
                                ))
                            ) : (
                                <p>You are not following anyone!</p>
                            )}
                        </div>
                    </div>
                </>
            )}
        </Webpage>
    )
}

function Profile({follower, setError, setSuccess, setFollowers})
{
    const handleAction = () =>
    {
        axios.post(`${API_ENDPOINT}/api/v1/profile/follow/${follower.username}`)
            .then(response => {
                if (response.status === 200)
                {
                    setFollowers(prevFollowers => prevFollowers.filter(f => f.followerId !== follower.followerId));
                    setSuccess('User unfollowed!');
                }
            })
            .catch(() => {
                setError('Failed to unfollow.');
            });
    };

    return (
        <div className="profile-header2">
            <div className="profile-header-avatar">
                <div className="profile-header-avatar-container">
                    <Avatar className="profile-header-avatar-entity">A</Avatar>
                </div>
            </div>
            <div className="profile-header-description">
                <div className="profile-header-description-top">
                    <Link to={`/profile/${follower.username}`} className="profile-link">
                        <h1 id="user-profile-name">{follower.username}</h1>
                    </Link>
                        <Button
                            onClick={handleAction}
                            class={"unfollow-button"}
                            disableRipple
                        >
                            {'Unfollow'}
                        </Button>
                </div>
                <div className="profile-header-description-bottom">
                    <p id="user-profile-description">{follower.description}</p>
                </div>
            </div>
        </div>
    )
}

export default FollowersPage;