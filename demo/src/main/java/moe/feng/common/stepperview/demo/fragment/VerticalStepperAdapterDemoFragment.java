package moe.feng.common.stepperview.demo.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import moe.feng.common.stepperview.IStepperViewAdapter;
import moe.feng.common.stepperview.VerticalStepperItemView;
import moe.feng.common.stepperview.VerticalStepperView;
import moe.feng.common.stepperview.demo.R;

public class VerticalStepperAdapterDemoFragment extends Fragment implements IStepperViewAdapter {

	private VerticalStepperView mVerticalStepperView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_vertical_stepper_adapter, parent, false);
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		mVerticalStepperView = view.findViewById(R.id.vertical_stepper_view);
		mVerticalStepperView.setViewAdapter(this);
	}

	@Override
	public String getTitle(int index) {
		return "Step " + index;
	}

	@Override
	public String getSummary(int index) {
		switch (index) {
			case 0:
				return "Summarized if needed";
			case 2:
				return "Last step";
			default:
				return null;
		}
	}

	@Override
	public int size() {
		return 3;
	}

	@Override
	public View onCreateCustomView(int index, Context context, VerticalStepperItemView parent) {
		View inflateView = LayoutInflater.from(context).inflate(R.layout.vertical_stepper_sample_item, parent, false);
		TextView contentView = inflateView.findViewById(R.id.item_content);
		contentView.setText(
				index == 0 ? R.string.content_step_0 : (index == 1 ? R.string.content_step_1 : R.string.content_step_2)
		);
		inflateView.findViewById(R.id.button_next).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if (!mVerticalStepperView.nextStep()) {
					Snackbar.make(mVerticalStepperView, "Finish", Snackbar.LENGTH_LONG).show();
				}
			}
		});
		inflateView.findViewById(R.id.button_prev).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				mVerticalStepperView.prevStep();
			}
		});
		return inflateView;
	}

	@Override
	public void onShow(int index) {

	}

	@Override
	public void onHide(int index) {

	}

}
