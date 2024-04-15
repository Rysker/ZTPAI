import React from "react";
import "../styles/Filter.css";
import {Checkbox, FormControlLabel, Divider} from "@material-ui/core";

export function Filter({title})
{
    return (
        <div className="filter">
            <div className="filter-title">
                {title}
            </div>
            <Divider orientation="horizontal"/>
            <div className="filter-content">
                <div className="filter-content-option">
                    <h1 className="filter-content-option-title">Scale</h1>
                    <FormControlLabel control={<Checkbox />} label="1:32" />
                    <FormControlLabel control={<Checkbox />} label="1:16" />
                    <FormControlLabel control={<Checkbox />} label="1:64" />
                    <FormControlLabel control={<Checkbox />} label="1:52" />
                    <FormControlLabel control={<Checkbox />} label="1:72" />
                </div>
                <div className="filter-content-option">
                    <h1 className="filter-content-option-title">Generation</h1>
                    <FormControlLabel control={<Checkbox />} label="<I" />
                    <FormControlLabel control={<Checkbox />} label="I" />
                    <FormControlLabel control={<Checkbox />} label="II" />
                    <FormControlLabel control={<Checkbox />} label="III" />
                    <FormControlLabel control={<Checkbox />} label="IV" />
                </div>
                <div className="filter-content-option">
                    <h1 className="filter-content-option-title">Country</h1>
                    <FormControlLabel control={<Checkbox />} label="Germany" />
                    <FormControlLabel control={<Checkbox />} label="Russia" />
                    <FormControlLabel control={<Checkbox />} label="USA" />
                    <FormControlLabel control={<Checkbox />} label="France" />
                    <FormControlLabel control={<Checkbox />} label="United Kingdom" />
                </div>
            </div>
        </div>
    )
}

export default Filter;