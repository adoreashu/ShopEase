document.addEventListener('DOMContentLoaded', () => {
    updateAuthNav();
});

function updateAuthNav() {
    const authNav = document.getElementById('auth-nav');
    if (!authNav) return;
    const token = localStorage.getItem('token');
    if (token) {
        authNav.innerHTML = 
            <li class="nav-item"><a class="nav-link" href="cart.html" data-testid="nav-cart">Cart (<span id="cart-count">0</span>)</a></li>
            <li class="nav-item"><a class="nav-link" href="order-history.html" data-testid="nav-orders">Orders</a></li>
            <li class="nav-item"><a class="nav-link" href="#" onclick="logout()" data-testid="nav-logout">Logout</a></li>
        ;
    } else {
        authNav.innerHTML = 
            <li class="nav-item"><a class="nav-link" href="login.html" data-testid="nav-login">Login</a></li>
            <li class="nav-item"><a class="nav-link" href="register.html" data-testid="nav-register">Register</a></li>
        ;
    }
}

function logout() {
    localStorage.removeItem('token');
    localStorage.removeItem('user');
    window.location.href = 'index.html';
}
