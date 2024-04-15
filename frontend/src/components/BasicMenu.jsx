import * as React from 'react';
import Button from '@mui/material/Button';
import Menu from '@mui/material/Menu';
import MenuItem from '@mui/material/MenuItem';
import {FaUser} from "react-icons/fa";
import {Link} from "react-router-dom";

export default function BasicMenu()
{
  const [anchorEl, setAnchorEl] = React.useState(null);
  const open = Boolean(anchorEl);
  const handleClick = (event) => {
    setAnchorEl(event.currentTarget);
  };
  const handleClose = () => {
    setAnchorEl(null);
  };

  return(
    <div className="user">
      <Button
        class="user-button"
        aria-controls={open ? 'basic-menu' : undefined}
        aria-haspopup="true"
        aria-expanded={open ? 'true' : undefined}
        onClick={handleClick}
      >
          <FaUser className="user-icon"></FaUser>
      </Button>
      <Menu
        id="basic-menu"
        anchorEl={anchorEl}
        open={open}
        onClose={handleClose}
        MenuListProps={{
          'aria-labelledby': 'basic-button',
        }}
      >
        <MenuItem class="menu-item" component={Link} to="/profile/1" onClick={handleClose}>Profile</MenuItem>
        <MenuItem class="menu-item" component={Link} to="/admin" onClick={handleClose}>Admin</MenuItem>
        <MenuItem class="menu-item" component={Link} to="/" onClick={handleClose}>Logout</MenuItem>
      </Menu>
    </div>
  );
}