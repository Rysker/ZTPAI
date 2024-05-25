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
import {Link, useParams} from "react-router-dom";
import VehicleKit from "../components/VehicleKit";
import Webpage from "../components/Webpage";

const API_ENDPOINT = process.env.REACT_APP_API_ENDPOINT;

function ModelKit({setError, setSuccess})
{

    const [vehicleKit, setVehicleKit] = useState([]);
    const [reviews, setReviews] = useState([])
    const { vehicle_name, id } = useParams();

    useEffect(() =>
    {
        fetchReviews();
    }, [vehicle_name]);

    const fetchReviews = async () =>
    {
        try
        {
            const response = await axios.get(`${API_ENDPOINT}/api/v1/models/${vehicle_name}/${id}`);
            setReviews(null);
            setVehicleKit(null);
            setReviews(response.data.reviews);
            setVehicleKit(response.data);
        }
        catch (error)
        {
            console.error('Error fetching reviews');
        }
    };

    const changeObserved = async (id) =>
    {
        const response = await axios.post(`${API_ENDPOINT}/api/v1/watchlist/change/${id}`);
        if(response.status !== 200)
            setError('Failed to change observed status.');
    };

    const addCollectible = async () =>
    {
        const response = await axios.post(`${API_ENDPOINT}/api/v1/collection/collectible/add/${id}`);
        if(response.status !== 200)
            setError('Failed to change observed status.');
    };

    return (
        <Webpage className={"webpage"}>
            {({ setError, setSuccess }) => (
                <>
                    <DefaultNavbar/>
                    <div className="content-space">
                        <div className="content-space-info">
                            <div className="content-space-info-display-row">
                                <VehicleKit key={id} kit={vehicleKit} changeObserved={changeObserved} addCollectible={addCollectible}/>
                                <PhotoGallery kit={vehicleKit}/>
                                <div className="content-space-reviews">
                                    <WriteReview setError={setError} setSuccess={setSuccess} id={id} reviews={reviews} fetchReviews={fetchReviews} />
                                    <ReviewHeader kit={vehicleKit}/>
                                    {reviews.map((review, index) => (
                                        <Review key={index} review={review} setError={setError} setSuccess={setSuccess} />
                                    ))}
                                </div>
                            </div>
                        </div>
                    </div>
                </>
            )}
        </Webpage>
    )
}

function WriteReview({ setError, setSuccess, id, fetchReviews, reviews })
{
    const [title, setTitle] = useState('');
    const [description, setDescription] = useState('');
    const [score, setScore] = useState(0.0);
    const username = localStorage.getItem("username");
    const [submittedReviewId, setSubmittedReviewId] = useState(false);

    useEffect(() =>
    {
        const userReview = reviews.find(review => review.username === username) || null;
        if(userReview !== null)
        {
            setSubmittedReviewId(userReview.reviewId);
            setTitle(userReview.title);
            setDescription(userReview.content);
            setScore(userReview.rating);

        }
    }, [reviews, username]);

    const handleReviewSubmit = async () =>
    {
        try
        {
            const response = await axios.post(`${API_ENDPOINT}/api/v1/review/add/${id}`, {
                title,
                description,
                score
            });
            if (response.status === 200)
            {
                setSubmittedReviewId(response.data.message);
                setSuccess('Review submitted successfully.');
                fetchReviews();
            }
            else
                setError('Failed to submit review.');
        }
        catch (error)
        {
            setError('Error submitting review.');
        }
    };

    const handleReviewDelete = async () =>
    {
        try
        {
            const response = await axios.delete(`${API_ENDPOINT}/api/v1/review/review/${submittedReviewId}`);
            if (response.status === 200)
            {
                setSubmittedReviewId(null);
                setTitle('');
                setDescription('');
                setScore(0.0);
                setSuccess('Review deleted successfully.');
                fetchReviews();
            }
            else
                setError('Failed to delete review.');

        }
        catch (error)
        {
            setError('Error deleting review.');
        }
    };

    return (
        <div className="write-review">
            <div className="write-review-inputs">
                <TextField
                    id="write-review-title"
                    label="Title"
                    variant="outlined"
                    value={title}
                    onChange={(e) => setTitle(e.target.value)}
                    sx={{
                        width: '40%',
                        height: '35%'
                    }}
                    disabled={!!submittedReviewId}
                />
                <TextField
                    id="write-review-content"
                    label="Review text"
                    variant="outlined"
                    value={description}
                    onChange={(e) => setDescription(e.target.value)}
                    maxRows={3}
                    multiline
                    sx={{ width: '70%', height: '55%' }}
                    disabled={!!submittedReviewId}
                />
            </div>
            <div className="write-review-buttons">
                <ClickableRating value={score} onChange={setScore} disabled={!!submittedReviewId} />
                <Button
                    id="submit-review"
                    sx={{ width: 'auto' }}
                    onClick={submittedReviewId ? handleReviewDelete : handleReviewSubmit}
                >
                    {submittedReviewId ? 'Delete review' : 'Add review'}
                </Button>
            </div>
        </div>
    );
}

function Review({review, setError, setSuccess})
{
    const [liked, setLiked] = React.useState(review.userLike === "true");
    const [disliked, setDisliked] = React.useState(review.userLike === "false");

    const reviewReport = async() =>
    {
        try
        {
            const response = await axios.post(`${API_ENDPOINT}/api/v1/report/review/${review.reviewId}`)
            if(response.status === 200)
                setSuccess("Review reported successfully")
            else
                setError("Report error!");
        }
        catch(error)
        {
            console.log("Unknown error!")
        }
    }

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
            console.error('Error liking review!');
        }
    };
    return(
        <div>
            <div className="review-entity">
                <div className="review-entity-description">
                    <RatingInformation value={review.rating}></RatingInformation>
                    <Link to={`/profile/${review.username}`} className="profile-link">
                        <h1>{review.username}</h1>
                    </Link>
                    <h2>{review.writeDate}</h2>
                </div>
                <Divider orientation="vertical" variant="middle" flexItem />
                <div className="review-entity-text">
                    <p id="review-title">{review.title}</p>
                    <h1 id="review-description">{review.content}</h1>
                </div>
                <div className="review-entity-actions">
                    <div className="vehicle-kit-info-controls">
                        <FaFlag
                            className="report-icon"
                            onClick={() => reviewReport()}
                        />
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