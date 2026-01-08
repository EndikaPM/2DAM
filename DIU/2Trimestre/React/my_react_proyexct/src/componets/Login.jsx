

function Login(props) {

    const user = {
        userName: "",
        email: ""
    }

    const handleSubmit =(e)=> {
        e.preventDefault();
        console.log(user);
        props.handleLogin(user)
    };

    const setUserName =(e)=>{
        user.userName = e.target.value;
    };
    const setEmail =(e)=>{
        user.email = e.target.value;
    };



    return (
        <section>
            <h2>Login Section</h2>
            <form onSubmit={handleSubmit}>
                <fieldset>
                    <label htmlFor="userName">User Name</label>
                    <input type="text" id="userName" onChange={setUserName}/>
                </fieldset>
                <fieldset>
                    <label htmlFor="email">Email</label>
                    <input type="text" id="email" onChange={setEmail}/>
                </fieldset>
                <button>Login</button>
            </form>
        </section>
    )
}

export default Login