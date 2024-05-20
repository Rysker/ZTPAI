import React, {useState, useEffect} from 'react';
import {FaFlag, FaThumbsDown, FaThumbsUp} from "react-icons/fa";
import '../App.css';
import '../styles/ModelKit.css'
import  "../styles/VehicleDetails.css";
import {DefaultNavbar} from "../components/DefaultNavbar";
import RatingInformation from "../components/RatingInformation";
import Button from "@material-ui/core/Button";
import {Divider} from "@material-ui/core";
import {TextField} from "@mui/material";
import ClickableRating from "../components/ClickableRating";
import axios from "axios";
import {useParams} from "react-router-dom";
import VehicleKit from "../components/VehicleKit";

const API_ENDPOINT = process.env.REACT_APP_API_ENDPOINT;

function ModelKit()
{
    const [vehicleKit, setVehicleKit] = useState([]);
    const [reviews, setReviews] = useState([])
    const { vehicle_name, id } = useParams();
    const [error, setError] = useState(null);

    useEffect(() =>
    {
        const fetchData = async () =>
        {
            try
            {
                const response = await axios.get(`${API_ENDPOINT}/api/v1/models/${vehicle_name}/${id}`);
                setVehicleKit(response.data);
                setReviews(response.data.reviews);
            }
            catch (error)
            {
                console.error('Error fetching data:', error);
            }
        };

        fetchData();
    }, [vehicle_name]);

    const changeObserved = async (id) =>
    {
        const response = await axios.post(`${API_ENDPOINT}/api/v1/watchlist/change/${id}`);
        if(response.status !== 200)
            setError('Failed to change observed status.');
    };

    const addCollectible = async (event) =>
    {
        const response = await axios.post(`${API_ENDPOINT}/api/v1/collection/add/${id}`);
        if(response.status !== 200)
            setError('Failed to change observed status.');
    };

    return (
        <div className="webpage">
            <DefaultNavbar/>
            <div className="content-space">
                <div className="content-space-info">
                    <div className="content-space-info-display-row">
                        <VehicleKit key={id} kit={vehicleKit} changeObserved={changeObserved} addCollectible={addCollectible}/>
                        <PhotoGallery kit={vehicleKit}/>
                        <div className="content-space-reviews">
                            <WriteReview/>
                            <ReviewHeader kit={vehicleKit}/>
                            {reviews.map((review, index) => (
                                <Review key={index} review={review} />
                            ))}
                        </div>
                    </div>
                </div>
            </div>
        </div>
    )
}

function WriteReview()
{
    return(
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
    )
}

function Review({review})
{
    const [liked, setLiked] = React.useState(review.userLike === "true");
    const [disliked, setDisliked] = React.useState(review.userLike === "false");
    const reviewLike = async (isLiked) =>
    {
        try
        {
            if ((isLiked && liked) || (!isLiked && disliked))
            {
                const previousState = isLiked;
                const response = await axios.delete(`${API_ENDPOINT}/api/v1/review/like/${review.reviewId}`);
                if (response.status === 200)
                {
                    setLiked(false);
                    setDisliked(false);
                    if(previousState)
                        review.score -= 1;
                    else
                        review.score += 1;
                }
            }
            else if((disliked && isLiked) || (liked && !isLiked))
            {
                const previousState = isLiked
                const response = await axios.post(`${API_ENDPOINT}/api/v1/review/like/swap/${review.reviewId}`);
                if (response.status === 200)
                {
                    setLiked(isLiked);
                    setDisliked(!isLiked);
                    if(previousState)
                        review.score += 2;
                    else
                        review.score -= 2;
                }
            }
            else
            {
                const response = await axios.post(`${API_ENDPOINT}/api/v1/review/like/${review.reviewId}`, { liked: isLiked });
                if (response.status === 200)
                {
                    setLiked(false);
                    setDisliked(false);
                    isLiked ? setLiked(true) : setDisliked(true);
                    if(isLiked)
                        review.score += 1;
                    else
                        review.score -= 1;
                }
            }
        }
        catch (error)
        {
            console.error('Error liking review:', error);
        }
    };
    return(
        <div>
            <div className="review-entity">
                <div className="review-entity-description">
                    <RatingInformation value={review.rating}></RatingInformation>
                    <h1>{review.username}</h1>
                    <h2>{review.writeDate}</h2>
                </div>
                <Divider orientation="vertical" variant="middle" flexItem />
                <div className="review-entity-text">
                    <p id="review-title">{review.title}</p>
                    <h1 id="review-description">{review.content}</h1>
                </div>
                <div className="review-entity-actions">
                    <div className="vehicle-kit-info-controls">
                        <FaFlag className="info-icon"></FaFlag>
                    </div>
                    <div className="vehicle-kit-info-reviews">
                        <FaThumbsUp
                            className={`thumbs ${liked ? 'liked' : ''}`}
                            onClick={() => reviewLike(true)}/>
                        <FaThumbsDown
                            className={`thumbs ${disliked ? 'disliked' : ''}`}
                            onClick={() => reviewLike(false)}/>
                        <span>{review.score}</span>
                    </div>
                </div>
            </div>
            <Divider orientation="horizontal" variant="middle"/>
        </div>
    )
}

function PhotoGallery({kit})
{
    let photos = kit.photos;
    let arr = [];
    if(typeof(photos) == "object" && photos.length > 0)
    {
        for(const photo of photos)
            arr.push(photo);
    }

    return(
        <div className="content-space-gallery">
            {arr.map((photo, index) => (
                <div className="content-space-gallery-entity" key={index}>
                    <img src={photo} alt={`Photo ${index + 1}`} />
                </div>
            ))}
        </div>
    );
}

function ReviewHeader({kit})
{
    return(
        <div className="reviews-header">
            <div className="reviews-header-title">Reviews</div>
            <RatingInformation value={Number(kit.reviewsAverage)}></RatingInformation>
            <span>({kit.reviewsCount})</span>
        </div>
    )
}
export default ModelKit;