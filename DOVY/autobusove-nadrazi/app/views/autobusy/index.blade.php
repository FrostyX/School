@extends('layouts.main')
@section('content')
	<h1>Autobusy</h1>
	<div class="panel panel-default">
		<div class="panel-heading">Zobrazen přehled od <b>{{ $od }}</b> do <b>{{ $do }}</b></div>
		<table class="table table-striped">
		<thead>
			<tr>
				<th><a href="/autobusy/spz">SPZ</a></th>
				<th><a href="/autobusy/spotreba">Spotřeba</a></th>
				<th><a href="/autobusy/ujel">Ujel</a></th>
				<th><a href="/autobusy/prumernaSpotreba">Průměrná spotřeba</a></th>
			</tr>
		</thead>
		<tbody>
			@foreach($autobusy as $autobus)
			<tr>
				<td>{{ $autobus->spz }}</td>
				<td>{{ $autobus->spotreba }} litrů/100km</td>
				<td>{{ $autobus->ujel }} km</td>
				<td>{{ $autobus->prumernaSpotreba }} litrů/den</td>
			</tr>
			@endforeach
		</tbody>
	</div>
@stop
