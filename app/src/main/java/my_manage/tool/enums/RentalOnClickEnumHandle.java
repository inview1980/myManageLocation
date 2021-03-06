package my_manage.tool.enums;

import android.app.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import my_manage.iface.IActivityMenu;
import my_manage.iface.IShowList;
import my_manage.tool.StrUtils;
import my_manage.ui.rent_manage.fragment.DialogFragmentChangeRepositAndRent;
import my_manage.ui.rent_manage.fragment.DialogFragmentContinueContract;
import my_manage.ui.rent_manage.fragment.DialogFragmentContinueProperty;
import my_manage.ui.rent_manage.fragment.DialogFragmentContinueRent;
import my_manage.ui.rent_manage.fragment.DialogFragmentPayProperty;
import my_manage.ui.rent_manage.listener.PersonExtendableListViewAdapterListener;
import my_manage.ui.rent_manage.listener.RentRoomExpandableListViewListener;
import my_manage.ui.rent_manage.listener.RoomListener;
//import my_manage.ui.rent_manage.page.ContinueContractActivity;
import my_manage.pojo.show.ShowRoomDetails;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum RentalOnClickEnumHandle implements IActivityMenu {
    ShowRoomDetails(100, "显示房屋详情") {
        @Override
        public <T extends Activity & IShowList> void run(T activity, List<ShowRoomDetails> data, int position) {
            RentRoomExpandableListViewListener.showDetails(activity, data, position);
        }
    }, ContinueRental(101, "续租") {
        @Override
        public <T extends Activity & IShowList> void run(T activity, List<my_manage.pojo.show.ShowRoomDetails> data, int position) {
            new DialogFragmentContinueRent(activity, data.get(position)).show(((AppCompatActivity) activity).getSupportFragmentManager(), "");
        }
    }, NotRent(102, "退租") {
        @Override
        public <T extends Activity & IShowList> void run(T activity, List<my_manage.pojo.show.ShowRoomDetails> data, int position) {
            RentRoomExpandableListViewListener.notRent(activity, data.get(position));
        }
    }, ChangeRent(103, "调整租金") {
        @Override
        public <T extends Activity & IShowList> void run(T activity, List<my_manage.pojo.show.ShowRoomDetails> data, int position) {
            new DialogFragmentChangeRepositAndRent(activity, data.get(position).getRentalRecord()::setMonthlyRent, data.get(position),
                    StrUtils.df4.format(data.get(position).getRentalRecord().getMonthlyRent()), "租金")
                    .show(((FragmentActivity) activity).getSupportFragmentManager(), "");
        }
    }, ChangeDeposit(104, "调整押金") {
        @Override
        public <T extends Activity & IShowList> void run(T activity, List<my_manage.pojo.show.ShowRoomDetails> data, int position) {
            new DialogFragmentChangeRepositAndRent(activity, data.get(position).getRentalRecord()::setDeposit, data.get(position),
                    StrUtils.df4.format(data.get(position).getRentalRecord().getDeposit()), "押金")
                    .show(((FragmentActivity) activity).getSupportFragmentManager(), "");
        }
    }, Record(105, "出租记录") {
        @Override
        public <T extends Activity & IShowList> void run(T activity, List<my_manage.pojo.show.ShowRoomDetails> data, int position) {
            RentRoomExpandableListViewListener.rentalHistory(activity, data.get(position));
        }
    }, Pay_PropertyCosts(106, "支付物业费") {
        @Override
        public <T extends Activity & IShowList> void run(T activity, List<my_manage.pojo.show.ShowRoomDetails> data, int position) {
            new DialogFragmentContinueProperty(data.get(position)).show(((FragmentActivity) activity).getSupportFragmentManager(), "");
        }
    }, ContinueContract(107, "续签合同") {
        @Override
        public <T extends Activity & IShowList> void run(T activity, List<my_manage.pojo.show.ShowRoomDetails> data, int position) {
            new DialogFragmentContinueContract(data.get(position)).show(((FragmentActivity) activity).getSupportFragmentManager(), "");
//            RentRoomExpandableListViewListener.continueOperation(activity, data.get(position), ContinueContractActivity.class);
        }
    }, ManDetails(108, "租户资料") {
        @Override
        public <T extends Activity & IShowList> void run(T activity, List<my_manage.pojo.show.ShowRoomDetails> data, int position) {
            PersonExtendableListViewAdapterListener.showPersonDetails(activity, data.get(position).getRentalRecord().getManID());
        }
    }, DelRoom(109, "删除房源") {
        @Override
        public <T extends Activity & IShowList> void run(T activity, List<my_manage.pojo.show.ShowRoomDetails> data, int position) {
            RentRoomExpandableListViewListener.delRoom(activity, data.get(position));
        }
    },PayProperty(110,"物业缴费情况"){
        @Override
        public <T extends Activity & IShowList> void run(T activity, List<my_manage.pojo.show.ShowRoomDetails> data, int position) {
            new DialogFragmentPayProperty(data.get(position).getRoomDetails().getCommunityName(),data.get(position).getRoomDetails().getRoomNumber())
                    .show(((AppCompatActivity)activity).getSupportFragmentManager(),"");
        }
    }
    
    , ShowRoomDetails2(200, "显示房屋详情") {
        @Override
        public <T extends Activity & IShowList> void run(T activity, List<ShowRoomDetails> data, int position) {
            RentRoomExpandableListViewListener.showDetails(activity, data, position);
        }
    }, RentalRoom(201, "出租") {
        @Override
        public <T extends Activity & IShowList> void run(T activity, List<my_manage.pojo.show.ShowRoomDetails> data, int position) {
            RentRoomExpandableListViewListener.rentRoom(activity, data.get(position));
        }
    }, Leaseback(202, "撤销退租") {
        @Override
        public <T extends Activity & IShowList> void run(T activity, List<my_manage.pojo.show.ShowRoomDetails> data, int position) {
            RentRoomExpandableListViewListener.leaseBack(activity, data.get(position));
        }
    }, Record2(203, "出租记录") {
        @Override
        public <T extends Activity & IShowList> void run(T activity, List<my_manage.pojo.show.ShowRoomDetails> data, int position) {
            RentRoomExpandableListViewListener.rentalHistory(activity, data.get(position));
        }
    }, DelRoom2(204, "删除房源") {
        @Override
        public <T extends Activity & IShowList> void run(T activity, List<my_manage.pojo.show.ShowRoomDetails> data, int position) {
            RentRoomExpandableListViewListener.delRoom(activity, data.get(position));
        }
    },PayProperty2(205,"物业缴费情况"){
        @Override
        public <T extends Activity & IShowList> void run(T activity, List<my_manage.pojo.show.ShowRoomDetails> data, int position) {
            PayProperty.run(activity,data,position);
        }
    },


    RecoverRoom(300, "恢复房源") {
        @Override
        public <T extends Activity & IShowList> void run(T activity, List<my_manage.pojo.show.ShowRoomDetails> data, int position) {
            RoomListener.recoverDel(activity, data.get(position));
        }
    }, DeleteRoom(301, "彻底删除房源") {
        @Override
        public <T extends Activity & IShowList> void run(T activity, List<my_manage.pojo.show.ShowRoomDetails> data, int position) {
            RoomListener.deleteRoom(activity, data.get(position));
        }
    }, Default(0, "默认") {
        @Override
        public <T extends Activity & IShowList> void run(T activity, List<my_manage.pojo.show.ShowRoomDetails> data, int position) {
        }
    },PayProperty3(302,"物业缴费情况"){
        @Override
        public <T extends Activity & IShowList> void run(T activity, List<my_manage.pojo.show.ShowRoomDetails> data, int position) {
            PayProperty.run(activity,data,position);
        }
    };

    private int id;
    private String details;

    public static RentalOnClickEnumHandle getType(int i) {
        for (final RentalOnClickEnumHandle value : RentalOnClickEnumHandle.values()) {
            if (value.id == i) return value;
        }
        return Default;
    }
}
