<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<%@ page import="com.pg.tool.ReadDXF"  %>
<%
	ArrayList alllist;
	ArrayList linelist=new ArrayList();
	ReadDXF readdxf=new ReadDXF("E:\\webgl\\works\\api-cw750-details.dxf");
	alllist = readdxf.getResult();
	if(alllist!=null && alllist.size()>0){
		  for(int i=0;i<alllist.size();i++)
		  {
			 if(alllist.get(i).toString().startsWith("[3,")){
				 linelist.add(alllist.get(i));
				 //System.out.println((alllist.get(i)));
			 }
		  } 
	}
	//if(linelist!=null && linelist.size()>0){
	//	  for(int i=0;i<linelist.size();i++)
	//	  {
	//	  	System.out.println("=="+(linelist.get(i)));
	//	  } 
	//}
%>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Three框架</title>
        <script src="js/three.js"></script>
        <style type="text/css">
            div#canvas-frame {
                border: none;
                cursor: pointer;
                width: 90%;
                height: 1200px;
                background-color: #22EE00;
            }
        </style>       
    </head>

    <body onload="threeStart();">
        <div id="canvas-frame"></div>
         <script>
            var renderer;
            function initThree() {
                width = document.getElementById('canvas-frame').clientWidth;
                height = document.getElementById('canvas-frame').clientHeight;
                renderer = new THREE.WebGLRenderer({
                    antialias : true
                });
                renderer.setSize(width, height);
                document.getElementById('canvas-frame').appendChild(renderer.domElement);
                renderer.setClearColor(0x22EE00, 1.0);
            }

            var camera;
            function initCamera() {
                camera = new THREE.PerspectiveCamera(45, width / height, 1, 10000);
                camera.position.x = 0;
                camera.position.y = 0;
                camera.position.z = 1000;
                camera.up.x = 0;
                camera.up.y = 0;
                camera.up.z = 0;
                camera.lookAt({
                    x : 0,
                    y : 0,
                    z : 0
                });
            }

            var scene;
            function initScene() {
                scene = new THREE.Scene();
            }

            var light;
            function initLight() {
                light = new THREE.DirectionalLight(0xFF0000, 1.0, 0);
                light.position.set(100, 100, 200);
                scene.add(light);
            }

            var cube;
            function initObject() {
				var str;
            	<%
            	if(linelist!=null && linelist.size()>0){
          		  for(int i=0;i<linelist.size();i++)
          		  {
          			%>
          			str = <%=linelist.get(i)%>;
          			str = str.toString();
          			var strs = str.split(",");
          			if(strs.length>0){
          			  var geometry = new THREE.Geometry();
                      var material = new THREE.LineBasicMaterial( { vertexColors: THREE.VertexColors} );
                      var color1 = new THREE.Color( 0x444444 ), color2 = new THREE.Color( 0xFF0000 );

                      // 线的材质可以由2点的颜色决定
                      var p1 = new THREE.Vector3( strs[1], strs[2], strs[3] );
                      var p2 = new THREE.Vector3( strs[4], strs[5], strs[5] );
                      geometry.vertices.push(p1);
                      geometry.vertices.push(p2);
                      geometry.colors.push( color1, color2 );

                      var line = new THREE.Line( geometry, material, THREE.LinePieces );
                      scene.add(line);
          			}                  
          		  	//System.out.println("=="+(linelist.get(i)));
          			<%
          		  } 
          		}
            	%>

            }
            function render()
            {
                renderer.clear();
                renderer.render(scene, camera);
                requestAnimationFrame(render);
            }
            function threeStart() {
                initThree();
                initCamera();
                initScene();
                initLight();
                initObject();
                render();
            }

        </script>
    </body>
</html>