import '../App.css';
import '../styles/MainPage.css';

function MainPage ()
{
    return (
        <div className="main-webpage">
            <div className="navbar">
                <div className="title">
                    <a href="/" id="a-home">ModelBase</a>
                </div>
                <div className ="buttons">
                </div>
            </div>
            <div className="webpage-body">
                <p><a href="/login">Join Us!</a></p>
            </div>
        </div>
    )
}

export default MainPage;