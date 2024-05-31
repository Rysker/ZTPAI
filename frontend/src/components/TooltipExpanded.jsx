import React from 'react';
import Tooltip from '@mui/material/Tooltip';
import Fade from '@mui/material/Fade';

const TooltipExpanded = ({ title, className, children}) =>
    (
    <Tooltip TransitionComponent={Fade} title={title} enterDelay={300} leaveDelay={100} disableInteractive>
        <span className={className}>{children}</span>
    </Tooltip>
);

export default TooltipExpanded