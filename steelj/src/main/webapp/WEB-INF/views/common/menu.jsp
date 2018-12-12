<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<ul class="sidebar navbar-nav">
        <li class="nav-item active">
          <a class="nav-link" href="main">
            <i class="fas fa-fw fa-tachometer-alt"></i>
            <span>Dashboard</span>
          </a>
        </li>
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="login" id="pagesDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
            <i class="fas fa-fw fa-user"></i>
            <span>User</span>
          </a>
          <div class="dropdown-menu" aria-labelledby="pagesDropdown">
            <a class="dropdown-item" href="register">Register</a>
            <a class="dropdown-item" href="login">Login Wallet</a>                        
          </div>
        </li>
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" id="pagesDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
            <i class="fas fa-fw fa-industry"></i>
            <span>Blocks</span></a>
          </a>
          <div class="dropdown-menu" aria-labelledby="pagesDropdown">
            <a class="dropdown-item" href="mining">Mining Status</a>
            <a class="dropdown-item" href="block">Block Status</a>                        
          </div>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="transaction-list">
            <i class="fas fa-fw fa-money-check"></i>
            <span>Transactions</span></a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="holder">
            <i class="fas fa-fw fa-money-bill"></i>
            <span>Holder</span>
          </a>
        </li>
        <!-- <li class="nav-item">
          <a class="nav-link" href="tables.html">
            <i class="fas fa-fw fa-money-bill"></i>
            <span>Money Transfer</span>
          </a>
        </li> -->
        <!-- <li class="nav-item">
          <a class="nav-link" href="tables.html">
            <i class="fas fa-fw fa-user"></i>
            <span>Search Job</span></a>
        </li> -->
      </ul>