import React, { useState } from 'react';
import Rating from '@mui/material/Rating';

function ControlledRating() {
    const [value, setValue] = useState(0);

    return (
        <Rating
            name="simple-controlled"
            value={value}
            onChange={(event, newValue) => {
                setValue(newValue);
            }}
        />
    );
}

export default ControlledRating;
