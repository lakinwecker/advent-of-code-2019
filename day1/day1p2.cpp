#include <iostream>
#include <range/v3/view.hpp>
#include <range/v3/algorithm.hpp>
#include <range/v3/functional.hpp>
#include <range/v3/numeric/accumulate.hpp>

using namespace std;
using namespace ranges;

int fuel(int mass) {
    int f = mass / 3 - 2;
    if (f <= 0) {
        return 0;
    } else {
        return f + fuel(f);
    }

}

struct to_int_fn {
    template <class Rng>
    int operator()(Rng && rng) const {
        int res = 0;
        RANGES_FOR (char ch, rng) {
            if (ch < '0' || ch > '9')
                throw std::invalid_argument("non-integral");
            res = res * 10 + (ch - '0');
        }
        return res;
    }
};

int main() {
    auto fuel_costs = ranges::getlines(cin)
                | views::transform(to_int_fn{})
                | views::transform(fuel);
    cout << ranges::accumulate(fuel_costs, 0) << endl;
}
