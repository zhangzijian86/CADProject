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
                width: 900px;
                height: 900px;
                background-color: #e6e6e6;
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
                renderer.setClearColor(0xe6e6e6, 1.0);
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
            var viewPort;
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
          			  var geometry = new THREE.Geometry()
          			  var material = new THREE.LineBasicMaterial({ linewidth: 1, color: 0x000000 });
                      // 线的材质可以由2点的颜色决定
                      var p1 = new THREE.Vector3( strs[1].toString().trim(), strs[2].toString().trim(), 0 );
                      var p2 = new THREE.Vector3( strs[4].toString().trim(), strs[5].toString().trim(), 0 );
                      geometry.vertices.push(p1);
                      geometry.vertices.push(p2);
                      var line = new THREE.Line( geometry, material);
                      scene.add(line);
          			}                  
          		  	//System.out.println("=="+(linelist.get(i)));
          			<%
          		  } 
          		}
            	%>
            	viewPort = getCameraParametersFromScene(1,scene);
             	//alert("=="+viewPort.left+"=="+ viewPort.right+"=="+ viewPort.top+"=="+ viewPort.bottom);
            }
            
            var camera;
            function initCamera() {
            	  //alert("=="+viewPort.left+"=="+ viewPort.right+"=="+ viewPort.top+"=="+ viewPort.bottom);
            	  camera = new THREE.OrthographicCamera(viewPort.left, viewPort.right, viewPort.top, viewPort.bottom, 1, 19);
            	  //camera = new THREE.PerspectiveCamera(45, width / height, 1, 10000);
                  //camera.position.x = 0;
                  //camera.position.y = 0;
                  //camera.position.z = 1000;
                  camera.position.z = 10;
        		  camera.position.x = viewPort.center.x;
                  camera.position.y = viewPort.center.y;
            }
            
            function render()
            {
                renderer.clear();
                renderer.render(scene, camera);
                requestAnimationFrame(render);
            }
            
            var controls
            function initOrbitControls()
            {
            	 controls.target.x = camera.position.x;
                 controls.target.y = camera.position.y;
                 controls.target.z = 0;
                 controls.zoomSpeed = 3;
            }
            
            function threeStart() {
                initThree();                
                initScene();
                initLight();
                initObject();
                initCamera();
                render();
            }
            //==three-dxf==
            function getCameraParametersFromScene(aspectRatio, scene) {
                var upperRightCorner, lowerLeftCorner, center,
                    extentsAspectRatio, vpUpperRightCorner, vpLowerLeftCorner,
                    width, height, header, i, viewports, viewport,
                    dir;
                
                var extents;
                if(scene) {
                    extents = findExtents(scene);
                    upperRightCorner = { x: extents.max.x, y: extents.max.y }
                    lowerLeftCorner = { x: extents.min.x, y: extents.min.y }
                }
                // If nothing found in dxf, use some abitrary defaults
                if(!lowerLeftCorner || !upperRightCorner) {
                    var halfWidth = 15 * aspectRatio;
                    var halfHeight = 15 * (1 / aspectRatio);
                    upperRightCorner = {
                        x: halfWidth,
                        y: halfHeight
                    };
                    lowerLeftCorner = {
                        x: -halfWidth,
                        y: -halfHeight
                    };
                };
                // Now that we have the corners, figure the current viewport extents
                width = upperRightCorner.x - lowerLeftCorner.x;
                height = upperRightCorner.y - lowerLeftCorner.y;
                center = center || {
                    x: width / 2 + lowerLeftCorner.x,
                    y: height / 2 + lowerLeftCorner.y
                };

                // fit all objects into current ThreeDXF viewer
                extentsAspectRatio = Math.abs(width / height);
                
                if(aspectRatio > extentsAspectRatio) {
                    width = height * aspectRatio;
                } else {
                    height = width / aspectRatio;
                }
                return {
                    bottom: -height / 2,
                    left: -width / 2,
                    top: height / 2,
                    right: width / 2,
                    center: {
                        x: center.x,
                        y: center.y
                    }
                }
            }
            
            function findExtents(scene) {
                var box = new THREE.Box3().setFromObject(scene);
                return box;
            }
           //==three-dxf==
        </script>
    </body>
</html>