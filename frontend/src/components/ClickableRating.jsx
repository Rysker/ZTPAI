import React, { useState } from 'react';
import Rating from '@mui/material/Rating';

function ClickableRating({ value, onChange, disabled = false })
{
    return (
        <Rating
            name="clickable-rating"
            value={value}
            onChange={(event, newValue) =>
            {
                onChange(newValue);
            }}
            disabled = {disabled}
        />
    );
}

export default ClickableRating;