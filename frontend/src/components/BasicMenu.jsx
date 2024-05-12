import * as React from 'react';
import Button from '@mui/material/Button';
import Menu from '@mui/material/Menu';
import MenuItem from '@mui/material/MenuItem';
import {FaUser} from "react-icons/fa";
import {Link} from "react-router-dom";
import axios from "axios";

export default function BasicMenu()
{
  const [anchorEl, setAnchorEl] = React.useState(null);
  const open = Boolean(anchorEl);
  const handleClick = (event) =>
  {
    setAnchorEl(event.currentTarget);
  };
  const handleClose = () => {
      setAnchorEl(null);
  };

    const handleLogout = async () =>
    {
        try
        {
            await axios.post('/api/v1/auth/logout');
            window.location.href = '/';
        }
        catch (error)
        {
            console.error('Error:', error.message);
            window.location.href = '/home';
        }
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
        <MenuItem class="menu-item" component={Link} to="/" onClick={handleLogout}>Logout</MenuItem>
      </Menu>
    </div>
  );
}