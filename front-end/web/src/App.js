import './App.css';

function App() {

  const reqBody = {
    "username":"jburch",
    "password":"password"
  }
  
  console.log(JSON.stringify(reqBody));
  fetch('http://localhost:8080/api/auth/login', {
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    },
    credentials: "same-origin",
    mode: "no-cors",
    method: "POST",
    body: JSON.stringify(reqBody)
  }).then((response) => Promise.all([response.json(), response.headers]))
  .then(([body, headers]) => {
    const authValue = headers.get("authorization");
    console.log(authValue);
    console.log(body);
  });

  return (
    <div className="App">
      <h1>Hello World</h1>
    </div>
  );
}

export default App;
