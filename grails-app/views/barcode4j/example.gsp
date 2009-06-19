<h1>Barcode4j Plugin</h1>

<p>
	This plugin provides a taglib that displays barcode. 
	It bases on barcode4j library (for more information please see: 
	<a href="http://barcode4j.sourceforge.net">http://barcode4j.sourceforge.net</a>).
</p>

<h2>Installation</h2>

<p>
To install plug-in run: 

<code>grails install-plugin barcode4j</code>

</p>
 
<h2>Usage</h2>

The simple example of usage barcode4j taglib:
<code>   
	&lt;g:barcode4j fmt="png" /&gt;
</code>

<p>
	It renders an example barcode as png image with example '123456' string.
	<br/>
	This tag allows an usage of the same parameters like barcode4j implementation:<br>
	<ul>
		<li>
			<p>	fmt: format of generated bardcode (e.g. png, jpeg, svg) - 
				<b style="color:red;">required</b>
			</p>
		</li>
		<li>type:type of barcode symbologies (e.g. codabar,code128,ean-8)</li>
		<li>message: number sequence to generate barcode (e.g. 123456)</li>
		<li>height: height of barcode</li>
		<li>wide-factor: </li>
		<li>module-width:</li>
	</ul>
</p>

The valid values of parameters depend on used type of barcode. To check rules, please see the 
<a href="http://barcode4j.sourceforge.net">barcode4j</a> documentation.  


<h3>Example of usage <h3/>

<p>
<table>
	<tr><td>
		Params: 
		<ul>
			<li>msg="7654312"</li>
			<li>fmt="png"</li>
		</ul>
	</td><td align="center">
		<g:barcode4j  msg="7654312" fmt="png"/>
	</td></tr>
	<tr><td>
		Params: 
		<ul>
			<li>type="codabar"</li>
			<li>msg="12345670"</li>
			<li>mw="0.15"</li>
			<li>height="8"</li>
			<li>fmt="png"</li>
		<ul> 
	</td><td align="center">
		<g:barcode4j type="codabar" msg="12345670" mw="0.15" height="8" fmt="png"/>
	</td></tr>
</table>
</p>
