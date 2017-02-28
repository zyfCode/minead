/**
 * @name Horn.Combox
 * @class
 * 下拉选项组件</br>
 * 替代html的select组件，有更加丰富的交互和功能
 */

/**@lends Horn.Combox# */
/**
 * 用于渲染列表中显示项目的实际值使用的item属性,默认为'text'，非必须，单值不能为空
 * @name Horn.Combox#<b>textField</b>
 * @type String
 * @default "text"
 * @example
 * 无
 */

/**
 * 组件的所属组，可以对相同组内的元素进行约束检查
 * @name Horn.Combox#group
 * @type String
 * @default ""
 * @ignore
 * @example
 * 验证指定对象$obj(scope)中组名为groupname的元素有有效性
 * Horn.Validate.validateAreaByGroup($obj,groupname)
 */


/**
 * 设置是否只读，设置为只读方式的组件，不可以编辑，可以通过下拉选择改变值，可以通过setValue、reset等API修改表单的值，并且可以参与表单提交<font color=red>(注！readonly属性仅对多选的情况下有效，单选无效)</font>
 * @function
 * @name Horn.Combox#setReadonly
 * @param {Boolean} readonly true表示只读，false表示正常
 */

/**
 * 设置表单的值
 * @function
 * @name Horn.Combox#setValue（value,triggerChange,type, notBlur）
 * @param {String} value 值
 * @param {Boolean} triggerChange 是否触发值更改事件
 * @param {Boolean} type值  true时获取文本值（优先判断）
* @param {Boolean} notBlur 是否触发值验证时间false时触发 
 */
/**
 * 获取表单的值
 * @function
 * @name Horn.Combox#getValue(value,type)
 *  @param {Boolean} value 值  false
 *   @param {Boolean} type 值  true时获取文本值（优先判断）
 * @return 表单的提交值
 */

/**
 * 获取由validate方法触发表单校验后的结果，并通过返回值标识校验的结果
 * @function
 * @name Horn.Combox#isValid
 * @return {Boolean} true表示校验通过，false表示校验失败
 */
/**
 * 触发校验表单的内容，然后通过调用isValid方法获取校验的结果
 * @function
 * @name Horn.Combox#validate
 */
/**冒泡处理
 *
function stopPropagation(e) {
     if (e.stopPropagation){
    	 e.stopPropagation(); 
     }else{
    	 e.cancelBubble = true;
     }                    
}
*/ 
/**点击页面上任意一处的事件
 *
$(document).bind('click',function(){
    $('.uv_textfield').find('div:eq(1)').css('display','none');
    //alert($('.uv_textfield >div:eq(1)').attr("id"));
});
*/ 
Horn.Ztree2 = Horn.extend(Horn.Field,{
	COMPONENT_CLASS:"Ztree2",
	pLabel : "",
	filterFlag : "",
	bodyclicktype : "click.ztree2" ,
	listEl : null ,
	ztree2 : null ,
	displayField : null,
	keyAttr : "label",
	pKeyAttr : "pkey",
	valueAttr : "value",
	defaultValue : "",
	defaultText : "",
	showLabel : true,
	timeover:true,
	/**
	 * @ignore
	 */
	init : function(dom) {
		Horn.Ztree2.superclass.init.apply(this,arguments) ;
		var _t=this;
		var url=this.params.url;
		this.hidden = this.el.find("input[type='hidden']");
	    this.field = this.hidden.next();
		this.textfieldObj=this.el.find("#tree_treeinput"+this.params.name);
		this.ztree2=this.textfieldObj;
		this.defaultValue = this.params.value || this.hidden.val();
		var t_name=this.params.name;
		this.listEl=$("#tree_treeinput"+t_name);
		var s2="treeinput"+t_name;
		_t.initreelist(t_name,url,s2);
		if(this.params.value!=null&&this.params.value!=''){
			_t.setValue(this.params.value);
		}
		this.field.bind({
            'focus' : Horn.Util.apply(_t.onFocus,_t) 
        });
		/**以下单击事件阻止冒泡事件
		 * 
		_t.bind({
            'click' : function(e){
            	stopPropagation(e); 
            }
       });
		this.field.bind({
            'focus' : function(e){
            	 $("#tree_"+s2).show();  
            }
       });
       
		this.textfieldObj.bind({
			'click' : function(e){
            	stopPropagation(e); 
           },
			'mouseleave':function(){
	        	   _t.validate();
	        }
       });
       */
		$("#"+this.params.name).bind({
			'click' : function(e){
				Horn.Util.stopPropagation(e);
           }
       });
	},
	/**
	 * @ignore
	 */
	bodyClick : function(e) {
		if(e.target==this.field.get(0)){
			$(document).one("click",Horn.Util.apply(this.bodyClick,this));
		}
		else{
			this.listEl.hide();	
		}
	} ,
	onFocus : function(e) {
        var curObj = $(e.currentTarget);
        var listDiv = this.listEl;
        this.showList(curObj, listDiv); 
    } ,
    showList : function(inputEl, listEl) {
        var _this = this ;
        listEl.show();
        
        var hidden = inputEl.prev() ;
        // 应用对象
        var data = {
            'inputEl' : inputEl,
            'listEl' : listEl
        };
         /*
		if(listEl.offsetParent() != inputEl.offsetParent() ){
			listEl.appendTo(inputEl.offsetParent());
		}
        var pos = inputEl.position(),
	       	listOuterHeight = inputEl.outerHeight();
        // 显示位置
        listEl.css("left",pos.left + 'px') ;
        listEl.css("top",(pos.top + listOuterHeight) + 'px') ;
        */
        // 显示
        listEl.css("display","block");
        // 文档事件处理
        $(document).one(_this.bodyclicktype, data, Horn.Util.apply(_this.bodyClick,_this));
     // 当前文本框事件处理
		listEl.bind('click', data, function(e) {
			Horn.Util.stopPropagation(e);
		});
		listEl.bind('mouseleave', data, function(e) {
			_this.validate();
		});
		inputEl.bind('mouseleave', data, function(e) {
			_this.validate();
		});
    } ,
	initreelist: function(t_name,url,s){
		  var trigger;
		  var   field=this;
		  var setting = {
				callback: {
				beforeClick: zTreeBeforeClick,
			    onClick: zTreeOnClick
		   },
	    //树形图动作
			view: {
						showIcon: showIconForTree,
						expandSpeed: "fast", //节点展开的速度
						selectedMulti: false //设置是否允许同时选中多个节点。
			},
			 data: {
					simpleData: {   //简单的数据源，一般开发中都是从数据库里读取，API有介绍，这里只是本地的                         
	         enable: true,
				   idKey: "id",  //id和pid，这里不用多说了吧，树的目录级别
				   pIdKey: "pId",
				   rootPId: 0   //根节点
				  }
				}
			};

			function showIconForTree(treeId, treeNode) {
				return !treeNode.isParent;
			};
			function zTreeBeforeClick(treeId, treeNode) {
				return !treeNode.isParent;
			};
			function zTreeOnClick(event, treeId, treeNode) {
		        $("#"+s).val(treeNode.name);
		        $("#show_"+s).val(treeNode.id);
		        $("#tree_"+s).hide();
		        field.validate();
	        };
		$.post(url, null, function(data) {
			if (data !=null) {
				 $.fn.zTree.init($("#treeDemo_treeinput"+t_name), setting, data);
			} else {
				Horn.Tip.warn(data);
			}
        }, "json"); 
	},
	
    validate : function(){
    	var _field = this;
    	if(!this.skipValid) {
			if(_field.params.check && _field.params.check=="required"){
	    		var v = _field.field.val();
	    		var _this2 =Horn.Validate;
	    		var obj2 = _field.field;
	    		var rules = _this2.getRules.call(_this2, obj2);
	    		 if (rules && rules.length > 0 && obj2.attr("disabled")==undefined) {
	 		        _this2.isValide.call(_this2, rules, _field, v);
	 	        }
	 	        else{
	 	            _this2.removeError.call(_this2, _field);
	 	        }
	    		 /*
	    		if(!v){
	    			_field.showError(Horn.Validate.regexEnum.requiredMessage);
	    		}
	    		*/
	    	}  
	    }
    	
    	
    },
    
    showError : function(errorMsg){
    	var field = this.field; 
    	field.removeClass('m-verify-success');
    	field.addClass('m-verify-error');
    	
    	errorMsg = $.type(errorMsg) == "boolean" ? "校验错误" : errorMsg;
    	if(!this.msgDiv){
    		this.msgDiv = $('<div class="m-verify-tip bottom" role="m-verify-tip" style="display: none;"></div>');
    		this.el.after(this.msgDiv);
    	}
        var msg = this.msgDiv;
        msg.html("<div class=\"verify-tip-arrow\"></div><div class=\"verify-tip-inner\">"+errorMsg+"</div>");
        msg.css("display", "block");
        this.err = true;
        
//    	var field = this.field; 
//    	errorMsg = $.type(errorMsg) == "boolean" ? "校验错误" : errorMsg;
//    	if(!this.msgDiv){
//    		this.msgDiv = $('<span class="hc_verification" style="margin-left:0px!important;display:none;"></span>');
//    		this.el.after(this.msgDiv);
//    	}
//        var msg = this.msgDiv;
//        msg.html(errorMsg);
//        msg.css("display", "none");
//        field.addClass('hc_ver-bd');
//        field.hover(function(){
//        	if(msg) msg.css("display", "inline");
//        },function(){
//        	if(msg) msg.css("display", "none");
//        });
//        this.err = true;  
       
    },
    /**
     * 删除错误信息
     * @function
     * @name Horn.Field#removeError
     * @ignore
     */
    removeError : function(){
    	this.field.removeClass('m-verify-error');
        var input = this.get();
        var check = input.attr(Horn.Validate.CHECK);
        if (check) {
        	if(this.isValid){
            	this.field.addClass('m-verify-success');
            }
        }
        this.err = false;
    	var msg = this.msgDiv;
    	if(msg) msg.remove();
    	delete this.msgDiv ;
    	
//    	this.field.removeClass('hc_ver-bd');
//        this.err = false;
//     	var msg = this.msgDiv;
//     	if(msg) msg.remove();
//     	delete this.msgDiv ;     
    },
    /*** @description 设置select的值
     * @function
     * @name Horn.Select#setValue
     * @param {String} value
     * @param {Boolean} type
     * @param {Boolean} triggerChange 是否触发值更改事件
     */
    setValue : function(value) {
        var hidden = this.hidden ;
        var field = this.field ;
        if (value === undefined || value === null) {
            	field.val("");
                hidden.val("");
                return false;
        }else{
        	//this.field.val(value);
            hidden.val(value);
            $.post(this.params.url, null, function(data) {
    			if (data != null) {
    				for ( var i = 0; i < data.length; i++) {
    					var node = data[i];
    					if (node.id ==value) {
    						field.val(node.name);
    					}
    				}
    			} else {
    				Horn.Tip.warn(data);
    			}
    		}, "json");
        }
    },
    /**
     * 获取提交的jQuery包装的field
     * @function
     * @name Horn.Field#get
     * @returns jQuery
     * @ignore
     */
    get : function(){
        if(this.hidden && this.hidden.length){
            return this.hidden ;
        }
        return this.field ;
    },
    getEventTarget : function() {
		return this.el.find("input[type='hidden']");
	},
    /**
     * 增加校验规则
     * @function
     * @name Horn.Field#addRule
     * @param {String} rule 校验规则字符串
     * @ignore
     */
    addRule : function(rule) {
        var input = this.get();
        var check = input.attr(Horn.Validate.CHECK);
        if (check) {
            if (check.indexOf(rule) > -1) {
                return;
            }
            check += Horn.Validate.CHECKSEP + rule;
        } else {
            check = rule;
        }
        input.attr(Horn.Validate.CHECK, check);
        if(rule && rule.indexOf(Horn.Validate.REQUIRED) > -1){
            var li = this.el.parent();
            var span = $("span:eq(0)",li);
            var red = $("b.hc_red", span);
            if (!red.length) {
                red = $("<b>", {
                    "class" : "hc_red",
                    "html" : "*"
                });
                span.prepend(red);
            } else {
                red.html("*");
            }
            
        }
        this.removeError();
    },
    /**
     * 删除校验规则
     * @function
     * @name Horn.Field#removeRule
     * @param {String} rule 校验规则字符串
     * @ignore
     */
    removeRule : function(rule) {
        var input = this.get();
        var check = input.attr(Horn.Validate.CHECK);
          //BUG #6518 【calendar】先进行非空校验的错误提示，然后调用removeRule("qq")，会造成非空校验的错误提示消失
        if (check && check.indexOf(rule) > -1) {//如果要去除的在原来的验证规则了就删除，否则不删除
            var checks = check.split(Horn.Validate.CHECKSEP);
            checks = $.grep(checks, function(c, index) {
                return c && c != rule;
            });
            input.attr(Horn.Validate.CHECK, checks.join(';'));
            this.removeError();
            this.setNotRequired();
        }
    },
    /**
     * 显示field字段，包含label部分
     * @function
     * @name Horn.Field#show
     * @ignore
     */
//    show : function() {
//    	this.skipValid=false;
//       // var li = this.el.parent();
//    	 var li = this.el.parent().parent(".g-unit-wrap");
//         //bug#17116 调用hidden控件的hide方法后再调用show方法，无法显示在原先位置
//         //hidden组件调用show方法会显示出来
//         if(this.el.attr("type")=="hidden"){
//         	return;
//         }
//        if (li.css("display") == "none") {
//            li.css("display", "block");
//        }
//        if (li.css("visibility") == "hidden") {
//            li.css("visibility", "visible");
//        }
//        //Horn.enterToTab(Horn.getCurrent());
//    },
    
    /**
     * 隐藏label字段，包含label部分
     * @function
     * @name Horn.Field#hide
     * @param {String} mode 取值为display或visibility
     * @ignore
     */
//    hide : function(mode) {//display   visibility
//    	this.skipValid=true;
//        mode =  mode || "display" ;
//        var li = this.el.parent();
//        if(mode=="display"){
//            li.css("display", "none");
//        }
//        else{
//            li.css("visibility", "hidden");
//        }
//        if(this.err){
//        	this.removeError();
//        }
//        Horn.enterToTab(Horn.getCurrent());
//    },
    /**
     * 设置为必填项，同时增加红色的 *
     * @function
     * @name Horn.Field#setRequired
     * @ignore
     */
    setRequired : function(required) {
    	if (required === false) {
    		this.setNotRequired();
    		return;
    	}
        this.addRule(Horn.Validate.REQUIRED);
    },
    /**
     * 设置为非必填，同时删除红色的 *
     * @function
     * @name Horn.Field#setNotRequired
     * @ignore
     */
//    setNotRequired : function() {
//        var li = this.el.parent();
//        var span = $("span", li);
//        var red = $("b.hc_red", span);
//        this.removeRule( Horn.Validate.REQUIRED);
//        red.html("");
//    }, 
    /**
     * 设置字段是否可用，设置为不可用，则不能提交
     * @function
     * @name Horn.Field#setEnable
     * @param {Boolean} enabled 如果为true设置为可用，设置为false，设置不可用
     * @ignore
     */
//    setEnable : function(enabled) {
//        var input = this.field;
//        var hidden = this.hidden;
//
//        var display = input.next("input[type='text']");
//        if (enabled) {
//            input.removeAttr("disabled");
//            display.removeAttr("disabled");
//            if(hidden) hidden.removeAttr("disabled");
//        } else {
//            input.attr("disabled", "disabled");
//            display.attr("disabled", "disabled");
//            if(hidden) hidden.attr("disabled", "disabled");
//        }
//        Horn.enterToTab(Horn.getCurrent());
//    },
    // 方法冗余
    setDisabled : function(disabled) {
    	if (disabled === false) {
    		this.setEnable(true);
            this.disabled = false;
    	} else {
    		//this.setNotRequired();
    		this.setEnable(false);
            this.disabled = true;
    	}
    },
    /**
     * 设置是否可编辑
     * @function
     * @name Horn.Field#setReadonly
     * @param {Boolean} readonly 不可编辑
     * @ignore
     */
    setReadonly : function(readonly) {
    	if (readonly === false) {
    		this.field.removeAttr("readonly");
    		this.readonly = false;
    	} else {
    		//BUG #6916 【textfield】【textarea】【password】"check": "required"情况下调用setReadonly(true)会取消掉非空校验
    		//this.setRequired(false);//为只读时不进行非空校验
    		this.field.attr("readonly", "readonly");
    		this.readonly = true;
    	}
//        this.field.attr("readonly", readOnly);
    },
    /**
     * 获取值
     * @function
     * @name Horn.Field#getValue
     * @return 返回field的实际值
     * @ignore
     */
    getValue : function() {
        var input = this.get();
        return input.val();
    },
    /**
     * 如果设置了defValue的值，重置成的defValue值，否则重置成初始值
     * @function
     * @name Horn.Field#reset
     * @param {String} 初始值
     * @ignore
     */
    reset : function(clear) {
    	var defValue = "";
    	if (this.hidden && this.hidden.length > 0) {
    		defValue = clear?"":this.defHiddenValue;
    	} else {
    		defValue = clear?"":this.defValue;
    	}
    	this.setValue(defValue);
    },
    /**
     * 清除值
     * @function
     * @name Horn.Field#clearValue
     * @ignore
     */
    clearValue : function() {
    	this.setValue("");
    },
    /**
     * 给field绑定事件
     * @function
     * @name Horn.Field#bind
     * @param {String} type 事件类型
     * @param {Object} data 事件绑定的数据
     * @param {Function} fn 事件绑定的方法
     * @ignore
     */
    bind : function(type, data, fn) {
        var input = this.get();
        if($.type(data)=="function"){
            input.bind( type, data);
        }
        else{
            input.bind( type, data, fn);
        }
    }
}) ;
Horn.Field.regFieldType("div.uv_textfield",Horn.Ztree2);