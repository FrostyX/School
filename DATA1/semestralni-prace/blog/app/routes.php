<?php

/*
|--------------------------------------------------------------------------
| Application Routes
|--------------------------------------------------------------------------
|
| Here is where you can register all of the routes for an application.
| It's a breeze. Simply tell Laravel the URIs it should respond to
| and give it the Closure to execute when that URI is requested.
|
*/

Route::get('/', 'HomeController@getIndex');

Route::get('/posts/new', 'HomeController@getSavePost');
Route::post('/posts/new', 'HomeController@postSavePost');

Route::get('/post/{slug}/edit', 'HomeController@getSavePost');
Route::post('/post/{slug}/edit', 'HomeController@postSavePost');

Route::get('/posts/{login}', 'HomeController@getIndex');
Route::get('/post/{slug}', 'HomeController@getPost');
Route::post('/post/{slug}', 'HomeController@postPost');

Route::get('/logout', 'LoginController@getLogout');
Route::get('/login', 'LoginController@getIndex');
Route::post('/login', 'LoginController@postIndex');
