package xinlan.com.AiAoBi.view;



import android.content.Context;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import xinlan.com.AiAoBi.R;
import xinlan.com.AiAoBi.utils.ToastUtils;

/**
 * 购物车自定义数量加减控件
 * @author XXW
 *
 */
public class JrComputeView extends LinearLayout {

	private int min = 1;
	private int max;
	private Button reduceButton, addButton;
	private EditText editText;
	private boolean isListen = true;
	private boolean isClick = true;
	private int maxNumLength;// 最大值数字位数
	Context context;
	public JrComputeView(Context context) {
		super(context);
		this.context=context;
		// TODO Auto-generated constructor stub
	}
	int num;
	public JrComputeView(final Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context=context;
		// 引用XML文件
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.view_compute, null);
		// 通过ID获取按钮和编辑文本
		reduceButton = (Button) view.findViewById(R.id.view_compute_reduceButton);
		addButton = (Button) view.findViewById(R.id.view_compute_addButton);
		editText = (EditText) view.findViewById(R.id.view_compute_EditText);
		// 设置文本输入位置
		editText.setSelection(editText.getText().toString().length());
		editText.setOnFocusChangeListener(new OnFocusChangeListener() {
			
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if(hasFocus){
						editText.setText(String.valueOf(num));


				}else{
					if(TextUtils.isEmpty(editText.getText().toString().trim())){
						editText.setText(String.valueOf(min));

					}
				}
			}
		});
		
		// 为编辑框绑定事件
		editText.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			}

			// 文本改变之后的事件
			@Override
			public void afterTextChanged(Editable arg0) {
				if(arg0.toString().equals("")||arg0.toString().equals("0")){
					return;
				}
				if (isListen) {
					try {
						int Count = min;
						if (arg0.toString().length() > 0) {
							Count = Integer.valueOf(arg0.toString());
							num=Count;
//							if (getNumLength(Count) > maxNumLength) {
//								int len = getNumLength(Count) - maxNumLength;
//								Count = Integer.valueOf(String.valueOf(Count).substring(len, String.valueOf(Count).length()));
//							} 
//							else if (Count > max && getNumLength(Count) == maxNumLength && 1 < maxNumLength) {
//								// 如果输入的数字大于最大值，那么把之前的数字最前的数字剔除
//								Count = Integer.valueOf(String.valueOf(Count).substring(1, String.valueOf(Count).length()));
//							}

						} else {
//							setNum(Count);
//							onEdtiTextBack(Count);
						}
						if (Count < min) {
							ToastUtils.show(context,"最少数量为" + min);
							setNum(min);
							onEdtiTextBack(min);
							reduceButton.setClickable(false);
							reduceButton.setEnabled(false);
							//reduceButton.setTextColor(Color.rgb(233, 233, 233));//非使能颜色:#E9E9E9
				//			reduceButton.setBackgroundResource(com.jinjinpai.shop.R.drawable.rectangle_shape_num_left);
						} else if (Count > Integer.valueOf(max)) {
							ToastUtils.show(context, "商品最大库存数量为" + max);
							setNum(max);
							onEdtiTextBack(max);
							addButton.setClickable(false);
							addButton.setEnabled(false);
							//addButton.setTextColor(Color.rgb(233, 233, 233));//非使能颜色:#E9E9E9
				//			addButton.setBackgroundResource(com.jinjinpai.shop.R.drawable.common_square_left_fram_gray_bg);
						} else {
//							setNum(Count);
							onEdtiTextBack(Count);
							if (Count < Integer.valueOf(max)) {
								if (!addButton.isClickable()) {
									addButton.setClickable(true);
									addButton.setEnabled(true);
									//addButton.setTextColor(Color.rgb(195, 195, 195));//使能颜色:#C3C3C3
					//				addButton.setBackgroundResource(com.jinjinpai.shop.R.drawable.common_square_left_fram_bg);
								}
							}
							if (Count > min) {
								if (!reduceButton.isClickable()) {
									reduceButton.setClickable(true);
									reduceButton.setEnabled(true);
									//reduceButton.setTextColor(Color.rgb(195, 195, 195));//使能颜色:#C3C3C3
				//					reduceButton.setBackgroundResource(com.jinjinpai.shop.R.drawable.rectangle_shape_num_right);
								}
							}
						}
						editText.setSelection(editText.getText().toString().length());
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
				}
			}
		});
		addView(view);
		/**
		 * 编辑框默认为1，所以—号按钮不能可用，不能点击
		 */
		// 设置reduceButton按钮颜色背景
		// reduceButton.setBackgroundResource(R.drawable.rectangle_shape_num_left);
		// // 设置不可用
		// reduceButton.setEnabled(false);
		// // 设置不能点击
		// reduceButton.setClickable(false);

		/**
		 * 为reduceButton按钮绑定事件
		 */

		reduceButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// 获取文本内容，并把字符串转整型
				// int Count=Integer.valueOf(editText.getText().toString());
				// Count--;
				// //判断Count大小
				// if(Count<=min){
				// // reduce按钮边框颜色变灰色
				// reduceButton.setBackgroundResource(R.drawable.rectangle_shape_num_left);
				// // 设置不可用
				// reduceButton.setEnabled(false);
				// // 设置不能点击
				// reduceButton.setClickable(false);
				// }
				// // 让文本显示内容
				// editText.setText(Count + "");
				// // 判断add按钮是否能点击，如果不能点击，设置add按钮，可点击，可用，并设置边框为黑色
				// if (!addButton.isClickable()) {
				// // 设置add按钮可点击
				// addButton.setClickable(true);
				// // 设置add按钮可用
				// addButton.setEnabled(true);
				// // 设置add按钮边框为黑色
				// addButton.setBackgroundResource(R.drawable.common_square_left_fram_bg);
				// }
				// if(back!=null){
				// back.reduceButtonBack(Count);
				// }

				int num = 0;
				if (editText.getText().toString().length() < 1) {
					num = 0;
				} else {
					num = Integer.valueOf(editText.getText().toString());
				}
				if (num > min) {
					num--;
					isListen = false;
					editText.setText(num + "");
					isListen = true;
				}
				if (num == min) {
					reduceButton.setClickable(false);
					reduceButton.setEnabled(false);
					//reduceButton.setTextColor(Color.rgb(233, 233, 233));//非使能颜色:#E9E9E9
			//		reduceButton.setBackgroundResource(com.jinjinpai.shop.R.drawable.rectangle_shape_num_left);
				}
				if (!addButton.isEnabled()) {
					addButton.setClickable(true);
					addButton.setEnabled(true);
					//addButton.setTextColor(Color.rgb(195, 195, 195));//使能颜色:#C3C3C3
			//		addButton.setBackgroundResource(com.jinjinpai.shop.R.drawable.common_square_left_fram_bg);
				}
				if (back != null) {
					back.reduceButtonBack(num);
				}
				editText.setSelection(editText.getText().toString().length());
			}
		});

		/**
		 * 为addButton绑定事件
		 */

		addButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// 获取文本内容，并把字符串转整型
				// int Count=Integer.valueOf(editText.getText().toString());
				// Count++;
				// //判断Count大小
				// if(Count>=max){
				// // reduce按钮边框颜色变灰色
				// addButton.setBackgroundResource(R.drawable.common_square_left_fram_gray_bg);
				// // 设置不可用
				// addButton.setEnabled(false);
				// // 设置不能点击
				// addButton.setClickable(false);
				// }
				// // 让文本显示内容
				// editText.setText(Count + "");
				// // 判断add按钮是否能点击，如果不能点击，设置add按钮，可点击，可用，并设置边框为黑色
				// if (!reduceButton.isEnabled()) {
				// // 设置add按钮可点击
				// reduceButton.setClickable(true);
				// // 设置add按钮可用
				// reduceButton.setEnabled(true);
				// // 设置add按钮边框为黑色
				// reduceButton.setBackgroundResource(R.drawable.rectangle_shape_num_right);
				// }
				// //让文本显示数据
				// editText.setText(Count+"");
				// if(back!=null){
				// back.addButtonBack(Count);
				// }

				int num = 0;
				if (editText.getText().toString().length() < 1) {
					num = 0;
				} else {
					num = Integer.valueOf(editText.getText().toString());
				}
				if (num < max) {
					num++;
					isListen = false;
					editText.setText(num + "");
					isListen = true;
				}
				if (num == max) {
					addButton.setClickable(false);
					addButton.setEnabled(false);
					//addButton.setTextColor(Color.rgb(233, 233, 233));//非使能颜色:#E9E9E9
			//		addButton.setBackgroundResource(com.jinjinpai.shop.R.drawable.common_square_left_fram_gray_bg);
				}
				if (!reduceButton.isEnabled()) {
					reduceButton.setClickable(true);
					reduceButton.setEnabled(true);
					//reduceButton.setTextColor(Color.rgb(195, 195, 195));//使能颜色:#C3C3C3
				//	reduceButton.setBackgroundResource(com.jinjinpai.shop.R.drawable.rectangle_shape_num_right);
				}
				if (back != null) {
					back.addButtonBack(num);
				}
				editText.setSelection(editText.getText().toString().length());
			}
		});
	}

	// 创建一个接口
	public interface CalculateCallBack {
		public void reduceButtonBack(int num);

		public void addButtonBack(int num);

		public void editTextChange(int num);
	}

	// 设置控件的操作回调
	private CalculateCallBack back;

	public void setCalculateCallBack(CalculateCallBack back) {
		this.back = back;
	}

	public void setMinNum(int min) {
		this.min = min;
	}

	public void setMaxNum(int max) {

		this.max = max;

		maxNumLength = getNumLength(max);

		if (max == 0) {
			if (isClick) {
				addButton.setClickable(false);
				addButton.setEnabled(false);
				//addButton.setTextColor(Color.rgb(233, 233, 233));//非使能颜色:#E9E9E9
			//	addButton.setBackgroundResource(com.jinjinpai.shop.R.drawable.common_square_left_fram_gray_bg);
				reduceButton.setClickable(false);
				reduceButton.setEnabled(false);
				//reduceButton.setTextColor(Color.rgb(233, 233, 233));//非使能颜色:#E9E9E9
			//	reduceButton.setBackgroundResource(com.jinjinpai.shop.R.drawable.rectangle_shape_num_left);
				isClick = false;
			} else if (!addButton.isEnabled()) {
				if (getNum() < max) {
					addButton.setClickable(true);
					addButton.setEnabled(true);
					//addButton.setTextColor(Color.rgb(195, 195, 195));//使能颜色:#C3C3C3
			//		addButton.setBackgroundResource(com.jinjinpai.shop.R.drawable.common_square_left_fram_bg);
				}
				isClick = true;
			}

		} else if (max >= 1) {
			addButton.setClickable(true);
			addButton.setEnabled(true);
			//addButton.setTextColor(Color.rgb(195, 195, 195));//使能颜色:#C3C3C3
		//	addButton.setBackgroundResource(com.jinjinpai.shop.R.drawable.common_square_left_fram_bg);

			if (max > 1) {
				reduceButton.setClickable(true);
				reduceButton.setEnabled(true);
				//reduceButton.setTextColor(Color.rgb(195, 195, 195));//使能颜色:#C3C3C3
		//		reduceButton.setBackgroundResource(com.jinjinpai.shop.R.drawable.rectangle_shape_num_right);
			} else {
				reduceButton.setClickable(false);
				reduceButton.setEnabled(false);
				//reduceButton.setTextColor(Color.rgb(233, 233, 233));//非使能颜色:#E9E9E9
			//	reduceButton.setBackgroundResource(com.jinjinpai.shop.R.drawable.rectangle_shape_num_left);

			}
		}

	}

	public int getMax() {
		return max;
	}

	public int getNum() {
		return Integer.valueOf(editText.getText().toString());
	}

	public void setNum(int num) {
		isListen = false;
		editText.setText(String.valueOf(num));
		this.num=num;
		
		
		if (num <= min) {
			reduceButton.setClickable(false);
			reduceButton.setEnabled(false);
			//reduceButton.setTextColor(Color.rgb(233, 233, 233));//非使能颜色:#E9E9E9
			reduceButton.setBackgroundResource(xinlan.com.AiAoBi.R.drawable.rectangle_shape_num_left);
			editText.setText(min + "");
			this.num=min;
			if (num<max) {
				addButton.setClickable(true);
				addButton.setEnabled(true);
				//addButton.setTextColor(Color.rgb(195, 195, 195));//使能颜色:#C3C3C3
			//	addButton.setBackgroundResource(com.jinjinpai.shop.R.drawable.common_square_left_fram_bg);
			}
			
		}
		
		
		
		if (num >= max) {
			addButton.setClickable(false);
			addButton.setEnabled(false);
			//addButton.setTextColor(Color.rgb(233, 233, 233));//非使能颜色:#E9E9E9
		//	addButton.setBackgroundResource(com.jinjinpai.shop.R.drawable.common_square_left_fram_gray_bg);
			editText.setText(String.valueOf(max));
			this.num=max;
			if (num>min) {
				reduceButton.setClickable(true);
				reduceButton.setEnabled(true);
				//reduceButton.setTextColor(Color.rgb(195, 195, 195));//使能颜色:#C3C3C3
		//		reduceButton.setBackgroundResource(com.jinjinpai.shop.R.drawable.rectangle_shape_num_right);
			}
		}
		

		isListen = true;
		
		
	}

	public EditText getEditText() {
		return editText;
	}

	private void onEdtiTextBack(int num) {
		if (null != back) {
			back.editTextChange(num);
		}
	}

	/**
	 * 取得数字位数
	 * 
	 * @param num
	 * @return
	 */
	private int getNumLength(int num) {
		return String.valueOf(num).length();
	}
	
}
