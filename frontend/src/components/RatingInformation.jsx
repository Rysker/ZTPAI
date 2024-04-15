import Rating from '@mui/material/Rating';
export function RatingInformation({value})
{
    return (
        <Rating className="info-rating" value={value} readOnly/>
    )
}

export default RatingInformation;