import React, {useState, useEffect} from 'react';
import {FaBinoculars, FaCheck, FaFlag, FaThumbsDown, FaThumbsUp} from "react-icons/fa";
import '../App.css';
import '../styles/ModelKit.css'
import  "../styles/VehicleDetails.css";
import {DefaultNavbar} from "../components/DefaultNavbar";
import RatingInformation from "../components/RatingInformation";
import Button from "@material-ui/core/Button";
import {Divider} from "@material-ui/core";
import {TextField} from "@mui/material";
import ClickableRating from "../components/ClickableRating";

function ModelKit()
{
    const [message, setMessage] = useState("");
    let photo = "/images/signupBackground.jpg";
    useEffect(() => {
        fetch('/hello')
            .then(response => response.text())
            .then(message => {
                setMessage(message);
            });
    },[])
    return (
        <div className="webpage">
            <DefaultNavbar></DefaultNavbar>
            <div className="content-space">
                <div className="content-space-info">
                    <div className="content-space-info-display-row">
                        <Kit name="Leopard 1" photo={photo}></Kit>
                        <div className="content-space-gallery">
                            <div className="content-space-gallery-entity">
                                <img src={photo}/>
                            </div>
                            <div className="content-space-gallery-entity">
                                <img src={photo} />
                            </div>
                            <div className="content-space-gallery-entity">
                                <img src={photo} />
                            </div>
                        </div>
                        <div className="content-space-reviews">
                            <div className="write-review">
                                <div className="write-review-inputs">
                                    <TextField
                                        id="write-review-title"
                                        label="Title"
                                        variant="outlined"
                                        sx={{
                                            width: '40%',
                                            height: '35%'
                                        }}
                                    />
                                    <TextField
                                        id="write-review-content"
                                        label="Review text"
                                        variant="outlined"
                                        maxRows={3}
                                        multiline
                                        sx={{width: '70%', height: '55%'}}
                                    />
                                </div>
                                <div className="write-review-buttons">
                                    <ClickableRating></ClickableRating>
                                    <Button
                                        id="submit-review"
                                        sx={{width:'auto'}}
                                    >
                                        Add review
                                    </Button>
                                </div>
                            </div>
                            <div className="reviews-header">
                                <div className="reviews-header-title">Reviews</div>
                                <RatingInformation value="4.5"></RatingInformation>
                                <span>(19)</span>
                            </div>
                            <Review value="4.3" user="User 1" date="19.02.2024" title="One of the best!" description="One of the best products you can buy! Definitely recommend it." sum="254"></Review>
                            <Divider orientation="horizontal" variant="middle"/>
                            <Review value="4.3" user="User 1" date="19.02.2024" title="One of the best!" description="One of the best products you can buy! Definitely recommend it." sum="254"></Review>
                            <Divider orientation="horizontal" variant="middle"/>
                            <Review value="4.3" user="User 1" date="19.02.2024" title="One of the best!" description="One of the best products you can buy! Definitely recommend it." sum="254"></Review>
                            <Divider orientation="horizontal" variant="middle"/>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    )
}

function Kit({name, photo})
{
    return(
        <div className="kit">
            <img src={photo} alt={name + " photo"} />
            <div className="kit-description">
                <div className="kit-description-title">
                    German Leopard 1 Main Battle Tank
                </div>
                <div className="kit-description-details">
                    <div className="kit-description-details-elements">
                        <p>Scale: 1:35</p>
                        <p>Scale: 1:35</p>
                        <p>Scale: 1:35</p>
                        <p>Scale: 1:35</p>
                    </div>
                </div>
                <Button id="kit-add">Add to collection</Button>
            </div>
            <div className="kit-info">
                <div className="kit-info-controls">
                    <FaCheck className="info-icon"></FaCheck>
                    <FaBinoculars className="info-icon"></FaBinoculars>
                </div>
                <div className="kit-info-reviews">
                    <RatingInformation value="4.6"></RatingInformation>
                    <p>(19)</p>
                </div>
            </div>
        </div>
    )
}

function Review({user, date, title, description, value, sum})
{
    return(
        <div className="review-entity">
            <div className="review-entity-description">
                <RatingInformation value={value}></RatingInformation>
                <h1>{user}</h1>
                <h2>{date}</h2>
            </div>
            <Divider orientation="vertical" variant="middle" flexItem />
            <div className="review-entity-text">
                <p id="review-title">{title}</p>
                <h1 id="review-description">{description}</h1>
            </div>
            <div className="review-entity-actions">
                <div className="vehicle-kit-info-controls">
                    <FaFlag className="info-icon"></FaFlag>
                </div>
                <div className="vehicle-kit-info-reviews">
                    <FaThumbsUp className= "thumbs"></FaThumbsUp>
                    <FaThumbsDown className= "thumbs"></FaThumbsDown>
                    <span>{sum}</span>
                </div>
            </div>
        </div>

    )
}
export default ModelKit;