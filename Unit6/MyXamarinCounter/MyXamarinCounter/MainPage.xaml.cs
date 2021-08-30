using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Xamarin.Forms;

namespace MyXamarinCounter
{
    public partial class MainPage : ContentPage
    {
        public int count {
            get; set;
        }

        public MainPage()
        {
            InitializeComponent();
        }

        public void MyIncrement(object sender, EventArgs args) {
            count += 1;
            label.Text = "" + count;
        }

        public void MyDecrement(object sender, EventArgs args) {
            count -= 1;
            label.Text = "" + count;
        }

    
    }
}
