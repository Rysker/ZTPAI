import React from "react";
import "../styles/Filter.css";
import { Divider, FormControlLabel, Checkbox } from "@material-ui/core";

function Filter({ title, filters, onCheckboxChange })
{
    const handleCheckboxChange = (filterTitle, option) =>
    {
        onCheckboxChange(filterTitle, option);
    };

    return (
        <div className="filter">
            <div className="filter-title">{title}</div>
            <Divider orientation="horizontal" />
            <div className="filter-content">
                {filters.map((filter, index) => (
                    <div key={index} className="filter-content-option">
                        <h1 className="filter-content-option-title">{capitalizeFirstLetter(filter.title)}</h1>
                        {filter.options.map((option, optionIndex) => (
                            <FormControlLabel
                                key={optionIndex}
                                control={
                                    <Checkbox
                                        onChange={() => handleCheckboxChange(filter.title, option)}
                                    />
                                }
                                label={option}
                            />
                        ))}
                    </div>
                ))}
            </div>
        </div>
    );
}

function capitalizeFirstLetter(string)
{
    return string.charAt(0).toUpperCase() + string.slice(1);
}

export default Filter;
